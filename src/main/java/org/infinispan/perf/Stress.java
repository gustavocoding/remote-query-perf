package org.infinispan.perf;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.InputStream;
import java.util.OptionalLong;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.configuration.XMLStringConfiguration;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xmx20G"})
@Command(name = "Stress", mixinStandardHelpOptions = true, version = "stress 0.1", description = "Simple stress test for Remote query.")
@SuppressWarnings("unused")
public class Stress implements Callable<Void> {
   private static final Random rand = new Random();

   public static final String DEFAULT_PROTOCOL = "3.0";
   public static final String DEFAULT_CACHE = "default";
   public static final String DEFAULT_SERVER = "127.0.0.1:11222";
   public static final String DEFAULT_THREADS = "10";
   public static final String DEFAULT_DURATION_MIN = "1";

   @Option(names = "-t", description = "The number of threads.", defaultValue = DEFAULT_THREADS)
   private int threads;

   @Option(names = "-d", description = "The duration in minutes.", defaultValue = DEFAULT_DURATION_MIN)
   private int durationMin;

   @Option(names = "-c", description = "The cache name.", defaultValue = DEFAULT_CACHE)
   @Param(value = DEFAULT_CACHE)
   private String cacheName;

   @Option(names = "-p", description = "HotRod protocol version.", defaultValue = DEFAULT_PROTOCOL)
   @Param(value = DEFAULT_PROTOCOL)
   private String protocol;

   @Option(names = "-s", description = "Server host and port", defaultValue = DEFAULT_SERVER)
   @Param(value = DEFAULT_SERVER)
   private String server;

   @Option(names = "-e", description = "Number of entries", required = true)
   @Param(value = "1000")
   private String entries;

   @Option(names = "-b", description = "Write batch", required = true)
   @Param(value = "1000")
   private String writeBatch;

   @Option(names = "-x", description = "Security", required = true)
   @Param(value = "1000")
   private String security;

   RemoteCache<String, Entity> cache;
   private QueryFactory queryFactory;
   private Query<Entity> query;
   private int max;

   public static void main(String[] args) {
      int exitCode = new CommandLine(new Stress()).execute(args);
      System.exit(exitCode);
   }

   @Override
   public Void call() throws Exception {
      Options opt = new OptionsBuilder()
            .include(Stress.class.getSimpleName())
            .measurementIterations(1)
            .threads(threads)
            .warmupIterations(0)
            .param("protocol", protocol)
            .param("server", server)
            .param("cacheName", cacheName)
            .param("batch", writeBatch)
            .param("entries", entries)
            .param("security", security)
            .measurementTime(new TimeValue(durationMin, TimeUnit.MINUTES))
            .output(String.format("result-%d.txt", System.currentTimeMillis()))
            .build();
      new Runner(opt).run();
      return null;
   }

   @Setup
   public void setup() {
      ConfigurationBuilder clientBuilder = new ConfigurationBuilder();
      clientBuilder.addServers(server + ";");
      clientBuilder.addContextInitializer(SCI.INSTANCE);
      if (Boolean.parseBoolean(security)) {
         clientBuilder.security().authentication().enable().username("user").password("user");
      }
      RemoteCacheManager rcm = new RemoteCacheManager(clientBuilder.build());

      String protoBufCacheName = ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME;
      rcm.getCache(protoBufCacheName).put("entity.proto", getProtoBuf());

      String cacheConfig = getCacheConfig().replace("NAME", cacheName);
      this.cache = rcm.administration().getOrCreateCache(cacheName, new XMLStringConfiguration(cacheConfig));

      Loader loader = new Loader(Integer.parseInt(entries), cache, Integer.parseInt(writeBatch));
      loader.run();
      max = Integer.parseInt(entries);
      QueryFactory queryFactory = Search.getQueryFactory(this.cache);
      query = queryFactory.create("FROM sample.Entity where ( rec1From <= :lower AND rec1To >= :upper )");
   }

   private String getCacheConfig() {
      return new Scanner(Stress.class.getResourceAsStream("/cache-config.xml"), UTF_8).useDelimiter("\\A").next();
   }

   private String getProtoBuf() {
      InputStream stream = Stress.class.getClassLoader().getResourceAsStream("proto/generated/entity.proto");
      return new Scanner(Stress.class.getResourceAsStream("/proto/generated/entity.proto"), UTF_8).useDelimiter("\\A").next();
   }

   @Benchmark
   @BenchmarkMode({Mode.SampleTime})
   public void loadGenerator(Blackhole blackhole) {
      int lowerBound = ThreadLocalRandom.current().nextInt(0, max / 2);
      int upperBound = ThreadLocalRandom.current().nextInt(max / 2, max);
      OptionalLong list = query.setParameter("lower", lowerBound).setParameter("upper", upperBound).execute().hitCount();
      blackhole.consume(list);
   }

}


