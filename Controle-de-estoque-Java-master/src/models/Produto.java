package models;

public class Produto {
    private String nome;
    private int id;
    private double preco;
    private double qtd;
    private String tipoUn;
    private double estoqueMin;
   // private String picPath;  <--Implementar depois(ou não)

    private Categoria cat = new Categoria();
    private Fornecedor forn = new Fornecedor();
    //GET and SET
    //-------x----------------x--------
   
   
    //-------x----------------x--------
    public double getEstoqueMin(){
        return this.estoqueMin;
    }
    public void setEstoqueMin(double estoqueMin){
        this.estoqueMin = estoqueMin;
    }
    //-------x----------------x--------
    /*
    public String getPicPath(){
        return this.picPath;
    }
    public void setPicPath(String picPath){
        this.picPath = picPath;
    }
    */
    //-------x----------------x--------
    public Fornecedor getForn(){
        return this.forn;
    }
    public void setForn(Fornecedor forn){
        this.forn = forn;
    }
    //-------x----------------x--------
 
    public void setCat(Categoria cat){
        this.cat = cat;
    }

    public String getQtdUn() { return this.qtd + " " + this.getQtdUn();}
    //-------x----------------x--------
    //Construtores
    //-------x----------------x--------
    public Produto(String nome, int id, double preco, double qtd, String tipoUn, double estoqueMin){
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.qtd = qtd;
        this.tipoUn = tipoUn;
        this.estoqueMin = estoqueMin;
    }

    public Produto(int id, String nome, double preco, double qtd, String tipoUn, Categoria cat) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
        this.tipoUn = tipoUn;
        this.cat = cat;
    }
    @Override
    public String toString(){
        return this.id + " - " + this.nome;
    }

    //-------x----------------x--------
    public Produto(){
    }
    //-------x----------------x--------
	public String getPreco1() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(double d) {
		// TODO Auto-generated method stub
		
	}
	public void setNome(String string) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTipoUn(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setQtd(double parseDouble) {
		// TODO Auto-generated method stub
	
	}
}
	