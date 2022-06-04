public class No<T> {
    public T dado;
    public No prox;

    public No() {
        this.prox = null;
    }

    public No(T dado, No<T> prox) {
        this.dado = dado;
        this.prox = prox;
    }
   
    public No(T dado) {
        this.dado = dado;
        this.prox = null;
    }
   
    public T getDado() {
        return dado;
    }
    
    public void setDado(T dado) {
        this.dado = dado;
    }
    
    public No getProx() {
        return prox;
    }
   
    public void setProx(No prox) {
        this.prox = prox;
    }

    public String toStringEncadeado() {
        String str = "No [conteudo=" + dado + "]";
        if(prox != null) {
            str += "->" + prox.toString();
        } else {
            str += "-> null";
        }

        return str;
    }
    
}
