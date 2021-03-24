package org.infinispan.perf;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.infinispan.client.hotrod.RemoteCache;

public class Loader {
   private static final List<String> wordList = new ArrayList<>();
   private static final Random rand = new Random();
   private static final int MAXIMUM_VALUE = 1_000_000;
   private final RemoteCache<String, Entity> cache;
   private final int entries;
   private final int writeBatch;


   public Loader(int entries, RemoteCache<String, Entity> cache, int writeBatch) {
      this.entries = entries;
      this.cache = cache;
      this.writeBatch = writeBatch;
   }

   static {
      try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("words.txt")) {
         assert inputStream != null;
         InputStreamReader streamReader = new InputStreamReader(inputStream, UTF_8);
         BufferedReader in = new BufferedReader(streamReader);

         for (String line; (line = in.readLine()) != null; ) {
            wordList.add(line);
         }
      } catch (IOException ioe) {
         throw new RuntimeException("Could not load words file!", ioe);
      }
   }


   public void run() {
      System.out.printf("Loading %d entries with write batch size of %d\n%n", entries, writeBatch);

      cache.clear();

      int nThreads = Runtime.getRuntime().availableProcessors();
      ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
      AtomicInteger counter = new AtomicInteger();
      CompletableFuture<?>[] futures = new CompletableFuture[nThreads];
      for (int i = 0; i < nThreads; i++) {
         futures[i] = CompletableFuture.supplyAsync(() -> {
            Map<String, Entity> group = new HashMap<>();
            for (int j = counter.incrementAndGet(); j <= entries; j = counter.incrementAndGet()) {
               group.put(String.valueOf(j), randomEntity(j));
               if (group.size() == writeBatch) {
                  cache.putAll(group);
                  System.out.printf("Loaded %s entries\r", j);
                  group = new HashMap<>();
               }
            }
            cache.putAll(group);
            return null;
         }, executorService);
      }
      System.out.println("\n");
      CompletableFuture.allOf(futures).join();
      executorService.shutdownNow();
   }

   public static long randomLong() {
      return rand.nextInt(MAXIMUM_VALUE);
   }

   public static Entity randomEntity(int id) {
      return new EntityBuilder()
            .setId(id)
            .setCode(randomWord())
            .setRec1From(randomLong())
            .setRec1To(randomLong())
            .setRec2From(randomLong())
            .setRec2To(randomLong())
            .setRec3From(randomLong())
            .setRec3To(randomLong())
            .setRec3From(randomLong())
            .setRec3To(randomLong())
            .setTd((int) randomLong())
            .setEd(randomWord())
            .setRc(randomWord())
            .setCau(randomWord())
            .setOr(randomWord())
            .setCd(randomWord())
            .setCu(randomWord())
            .setLuu(randomWord())
            .setLud(randomWord())
            .createEntity();
   }

   public static String randomWord() {
      return wordList.get(rand.nextInt(wordList.size()));
   }

}
