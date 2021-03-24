package org.infinispan.perf;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
      includeClasses = Entity.class,
      schemaFileName = "entity.proto",
      schemaFilePath = "proto/generated",
      schemaPackageName = "sample"
)
interface SCI extends SerializationContextInitializer {
   SCI INSTANCE = new SCIImpl();
}