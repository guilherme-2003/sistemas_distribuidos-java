package banco;

public class Cadastro {
    private Cliente lista[];
    private int count;
    
    public Cadastro(int size) {
        lista = new Cliente[size];
        count = 0;
    }
    
    public boolean inserir(Cliente cliente) {
        if(count > lista.length){
            lista[count] = cliente;
            count++;
            retorn true;
        }else{
            return false;
        }
    }
}