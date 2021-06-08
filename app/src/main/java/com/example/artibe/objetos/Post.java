package com.example.artibe.objetos;

public class Post {
    public int tipoblog;
    public String texto;
    public String url;
    public String urlimg;
    public String nomusuario;
    public String emailusuario;
    public String id;
    public Post(){

    }

    public Post(int tipoblog, String texto, String url,String urlimg,String nomusuario, String emailusuario,String id){
        this.tipoblog = tipoblog;
        this.texto = texto;
        this.url = url;
        this.urlimg = urlimg;
        this.nomusuario = nomusuario;
        this.emailusuario = emailusuario;
        this.id = id;
    }

    public int getTipoblog(){ return tipoblog; }

    public void setTipoblog(int tipoblog){ this.tipoblog = tipoblog; }


    public String getTexto(){ return texto; }

    public void setTexto(String texto){ this.texto = texto; }


    public String getUrl(){ return url; }

    public void setUrl(String url){ this.url = url; }



    public String getUrlimg(){ return urlimg; }

    public void setUrlimg(String urlimg){ this.urlimg = urlimg; }



    public String getNomusuario(){ return nomusuario; }

    public void setNomusuario(String nomusuario){ this.nomusuario = nomusuario; }



    public String getEmailusuario(){ return emailusuario; }

    public void setEmailusuario(String emailusuario){ this.emailusuario = emailusuario; }



}
