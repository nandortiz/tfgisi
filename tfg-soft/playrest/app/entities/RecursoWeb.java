package entities;

public class RecursoWeb {

    protected Integer id;
    protected String url;

    public RecursoWeb(){
        this.id=-1;
        this.url="";
    }



    public RecursoWeb(Integer id, String url){
        this.id = id;
        this.url = url;
    }

    public Integer getId(){
        return id;

    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public String toString(){
        return"RecursoWeb{" +"id= "+id +", URL= '" + url +'}';
    }
}
