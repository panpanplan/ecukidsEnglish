package MySql.model;

public class selectclassModel {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdescb() {
        return cdescb;
    }

    public void setCdescb(String cdescb) {
        this.cdescb = cdescb;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private Integer id;
    private String cid;
    private String cname;
    private String cdescb;
    private String sid;
}
