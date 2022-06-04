public class ListaEncOrdenada<T extends Comparable<T>> extends ListaEnc<T> {

    No<T> inicio;
    int tamanho;

    public ListaEncOrdenada() {
        this.inicio = null;
    }

    @Override
    public void incluir(T elemento) throws Exception {
        No<T> novoNo = new No<>(elemento);
        if (this.estaVazio()) {
            inicio = novoNo;
        } else {
            No<T> noAux = inicio;
            while (noAux.prox != null) {
                noAux = noAux.prox;
            }

            if (noAux.dado.compareTo(novoNo.dado) < 0) {
                noAux.prox = novoNo;
            } else {
                System.out.println("Não foi possível a inserção!");
            }
        }
    }

    @Override
    public void incluirInicio(T elemento) throws Exception {
        No<T> novoNo = new No<>(elemento);
        No<T> aux = inicio;

        if (this.estaVazio()) {
            inicio = novoNo;
        } else if (aux.getDado().compareTo(elemento) > 0) {
            if (aux == inicio) {
                novoNo.prox = inicio;
                inicio = novoNo;
            }
        } else {
            throw new Exception("Não foi possível adicionar no começo!");
        }
    }

    @Override
    public void incluir(T elemento, int posicao) throws Exception {
        if (posicao <= 0) {
            this.incluirInicio(elemento);
        } else if (posicao >= this.getTamanho()) {
            this.incluir(elemento);
        } else {
            No<T> aux = inicio;
            for (int i = 0; i < posicao - 1; i++) {
                aux = aux.prox;
            }
            No<T> novoNo = new No<>(elemento);
            if (((Comparable<T>) aux.prox.dado).compareTo(novoNo.dado) > 0 && aux.dado.compareTo(novoNo.dado) < 0) {
                novoNo.prox = aux.prox;
                aux.prox = novoNo;

            } else {
                throw new Exception("Não foi possível adicionar nessa posição!");
            }

        }
    }

    @Override
    public T get(int posicao) throws Exception {
        return getNo(posicao).getDado();
    }

    @Override
    public int get(T elemento) throws Exception {
        if (this.estaVazio())
            exceptionListaVazia();

        No<T> aux = inicio;
        int pos = 0;
        while (aux != null) {
            if (aux.dado.compareTo(elemento) == 0) {
                return pos;
            }
            pos++;
            aux = aux.prox;
        }
        throw new Exception("Elemento não existe!");
    }

    @Override
    public void remover(int posicao) throws Exception {
        if(this.estaVazio())
            exceptionListaVazia();
        if(posicao < 0 )
            System.out.println("posição invalida");
        if(posicao > this.tamanho)
           throw new Exception("Posição inválida!");

        this.validaPosicao(posicao);
        No<T> noAuxiliar = getNo(posicao);
        if (posicao == 0) {
            inicio = noAuxiliar.getProx();
            return;
        }
        No<T> noAnterior = getNo(posicao - 1);
        noAnterior.prox = noAuxiliar.prox;
    }

    @Override
    public void limpar() {
        this.inicio = null;
    }

    public void setTipoOrdenacao(boolean crescente) throws Exception {
        if (crescente) {
            inverter();
            inverter();
        }
        if (!crescente)
            inverter();

    }

    public void inverter() {
        No ant = null;
        No prox = null;
        No atual = inicio;
        while (atual != null) {
            prox = atual.getProx();
            atual.setProx(ant);
            ant = atual;
            atual = prox;
        }
        inicio = ant;
    }

    @Override
    public int getTamanho() {
        int tamanhoLista = 0;
        No<T> noAux = inicio;
        while (true) {
            if (noAux != null) {
                tamanhoLista++;
                if (noAux.getProx() != null) {
                    noAux = noAux.getProx();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return tamanhoLista;
    }

    @Override
    public boolean contem(T elemento) throws Exception {
        if (this.estaVazio())
            exceptionListaVazia();

        No<T> aux = inicio;
        while (aux != null) {
            if (aux.dado.compareTo(elemento) == 0) {
                return true;
            }
            aux = aux.prox;
        }
        return false;
    }

    public boolean estaVazio() {
        return inicio == null ? true : false;
    }

    private No<T> getNo(int posicao) throws Exception {
        validaPosicao(posicao);
        No<T> noAux = inicio;
        No<T> noRetorno = null;
        if (posicao < 0 && posicao > tamanho) {
            throw new Exception("Posição inválida!");
        }
        for (int i = 0; i <= posicao; i++) {
            noRetorno = noAux;
            noAux = noAux.getProx();
        }
        return noRetorno;
    }

    public void validaPosicao(int pos) {
        if (pos > getTamanho()) {
            int ultimoIndice = getTamanho() - 1;
            throw new IndexOutOfBoundsException(
                    "Não existe conteúdo no índice " + pos + " desta lista. Índice da lista: "
                            + ultimoIndice);
        }
    }

    public void exceptionListaVazia() throws Exception {
        throw new Exception("Lista está vazia!");
    }

    @Override
    public String toString() {
        String strRetorno = "";
        No<T> noAuxiliar = inicio;
        for (int i = 0; i < getTamanho(); i++) {
            strRetorno += "[No{conteudo=" + noAuxiliar.getDado() + "}]--->";
            if (noAuxiliar.prox != null)
                noAuxiliar = noAuxiliar.getProx();
        }
        strRetorno += "null";
        return strRetorno;
    }

}
