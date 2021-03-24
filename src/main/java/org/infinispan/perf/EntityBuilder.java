package org.infinispan.perf;

public class EntityBuilder {
   private int id;
   private String code;
   private long rec1From;
   private long rec1To;
   private long rec2From;
   private long rec2To;
   private long rec3From;
   private long rec3To;
   private long rec4From;
   private long rec4To;
   private int td;
   private String ed;
   private String rc;
   private String cau;
   private String or;
   private String cd;
   private String cu;
   private String luu;
   private String lud;

   public EntityBuilder setId(int id) {
      this.id = id;
      return this;
   }

   public EntityBuilder setCode(String code) {
      this.code = code;
      return this;
   }

   public EntityBuilder setRec1From(long rec1From) {
      this.rec1From = rec1From;
      return this;
   }

   public EntityBuilder setRec1To(long rec1To) {
      this.rec1To = rec1To;
      return this;
   }

   public EntityBuilder setRec2From(long rec2From) {
      this.rec2From = rec2From;
      return this;
   }

   public EntityBuilder setRec2To(long rec2To) {
      this.rec2To = rec2To;
      return this;
   }

   public EntityBuilder setRec3From(long rec3From) {
      this.rec3From = rec3From;
      return this;
   }

   public EntityBuilder setRec3To(long rec3To) {
      this.rec3To = rec3To;
      return this;
   }

   public EntityBuilder setRec4From(long rec4From) {
      this.rec4From = rec4From;
      return this;
   }

   public EntityBuilder setRec4To(long rec4To) {
      this.rec4To = rec4To;
      return this;
   }

   public EntityBuilder setTd(int td) {
      this.td = td;
      return this;
   }

   public EntityBuilder setEd(String ed) {
      this.ed = ed;
      return this;
   }

   public EntityBuilder setRc(String rc) {
      this.rc = rc;
      return this;
   }

   public EntityBuilder setCau(String cau) {
      this.cau = cau;
      return this;
   }

   public EntityBuilder setOr(String or) {
      this.or = or;
      return this;
   }

   public EntityBuilder setCd(String cd) {
      this.cd = cd;
      return this;
   }

   public EntityBuilder setCu(String cu) {
      this.cu = cu;
      return this;
   }

   public EntityBuilder setLuu(String luu) {
      this.luu = luu;
      return this;
   }

   public EntityBuilder setLud(String lud) {
      this.lud = lud;
      return this;
   }

   public Entity createEntity() {
      return new Entity(id, code, rec1From, rec1To, rec2From, rec2To, rec3From, rec3To, rec4From, rec4To, td, ed, rc, cau, or, cd, cu, luu, lud);
   }
}