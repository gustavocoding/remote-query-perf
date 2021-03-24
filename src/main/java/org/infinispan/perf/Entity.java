package org.infinispan.perf;

import java.util.Objects;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoDoc("@Indexed")
public class Entity {

   @ProtoField(number = 1)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   Integer id;

   @ProtoField(number = 2)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String code;

   @ProtoField(number = 3)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec1From;

   @ProtoField(number = 4)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec1To;

   @ProtoField(number = 5)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec2From;

   @ProtoField(number = 6)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec2To;

   @ProtoField(number = 7)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec3From;

   @ProtoField(number = 8)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec3To;

   @ProtoField(number = 9)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec4From;

   @ProtoField(number = 10)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.NO)")
   Long rec4To;

   @ProtoField(number = 11)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   Integer td;

   @ProtoField(number = 12)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String ed;

   @ProtoField(number = 13)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String rc;

   @ProtoField(number = 14)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String cau;

   @ProtoField(number = 15)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String or;

   @ProtoField(number = 16)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String cd;

   @ProtoField(number = 17)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String cu;

   @ProtoField(number = 18)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String luu;

   @ProtoField(number = 19)
   @ProtoDoc("@Field(index=Index.NO, analyze = Analyze.NO, store = Store.NO)")
   String lud;

   @ProtoFactory
   Entity(Integer id, String code, Long rec1From, Long rec1To, Long rec2From, Long rec2To, Long rec3From, Long rec3To,
          Long rec4From, Long rec4To, Integer td, String ed, String rc, String cau, String or, String cd, String cu,
          String luu, String lud) {
      this.id = id;
      this.code = code;
      this.rec1From = rec1From;
      this.rec1To = rec1To;
      this.rec2From = rec2From;
      this.rec2To = rec2To;
      this.rec3From = rec3From;
      this.rec3To = rec3To;
      this.rec4From = rec4From;
      this.rec4To = rec4To;
      this.td = td;
      this.ed = ed;
      this.rc = rc;
      this.cau = cau;
      this.or = or;
      this.cd = cd;
      this.cu = cu;
      this.luu = luu;
      this.lud = lud;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public Long getRec1From() {
      return rec1From;
   }

   public void setRec1From(Long rec1From) {
      this.rec1From = rec1From;
   }

   public Long getRec1To() {
      return rec1To;
   }

   public void setRec1To(Long rec1To) {
      this.rec1To = rec1To;
   }

   public Long getRec2From() {
      return rec2From;
   }

   public void setRec2From(Long rec2From) {
      this.rec2From = rec2From;
   }

   public Long getRec2To() {
      return rec2To;
   }

   public void setRec2To(Long rec2To) {
      this.rec2To = rec2To;
   }

   public Long getRec3From() {
      return rec3From;
   }

   public void setRec3From(Long rec3From) {
      this.rec3From = rec3From;
   }

   public Long getRec3To() {
      return rec3To;
   }

   public void setRec3To(Long rec3To) {
      this.rec3To = rec3To;
   }

   public Long getRec4From() {
      return rec4From;
   }

   public void setRec4From(Long rec4From) {
      this.rec4From = rec4From;
   }

   public Long getRec4To() {
      return rec4To;
   }

   public void setRec4To(Long rec4To) {
      this.rec4To = rec4To;
   }

   public Integer getTd() {
      return td;
   }

   public void setTd(Integer td) {
      this.td = td;
   }

   public String getEd() {
      return ed;
   }

   public void setEd(String ed) {
      this.ed = ed;
   }

   public String getRc() {
      return rc;
   }

   public void setRc(String rc) {
      this.rc = rc;
   }

   public String getCau() {
      return cau;
   }

   public void setCau(String cau) {
      this.cau = cau;
   }

   public String getOr() {
      return or;
   }

   public void setOr(String or) {
      this.or = or;
   }

   public String getCd() {
      return cd;
   }

   public void setCd(String cd) {
      this.cd = cd;
   }

   public String getCu() {
      return cu;
   }

   public void setCu(String cu) {
      this.cu = cu;
   }

   public String getLuu() {
      return luu;
   }

   public void setLuu(String luu) {
      this.luu = luu;
   }

   public String getLud() {
      return lud;
   }

   public void setLud(String lud) {
      this.lud = lud;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Entity entity = (Entity) o;
      return Objects.equals(id, entity.id) && Objects.equals(code, entity.code) && Objects.equals(rec1From, entity.rec1From) && Objects.equals(rec1To, entity.rec1To) && Objects.equals(rec2From, entity.rec2From) && Objects.equals(rec2To, entity.rec2To) && Objects.equals(rec3From, entity.rec3From) && Objects.equals(rec3To, entity.rec3To) && Objects.equals(rec4From, entity.rec4From) && Objects.equals(rec4To, entity.rec4To) && Objects.equals(td, entity.td) && Objects.equals(ed, entity.ed) && Objects.equals(rc, entity.rc) && Objects.equals(cau, entity.cau) && Objects.equals(or, entity.or) && Objects.equals(cd, entity.cd) && Objects.equals(cu, entity.cu) && Objects.equals(luu, entity.luu) && Objects.equals(lud, entity.lud);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, code, rec1From, rec1To, rec2From, rec2To, rec3From, rec3To, rec4From, rec4To, td, ed, rc, cau, or, cd, cu, luu, lud);
   }
}
