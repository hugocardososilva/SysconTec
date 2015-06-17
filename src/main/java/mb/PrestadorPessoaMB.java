package mb;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;

import dao.DAO;
import dao.DAOInformacao;
import dao.DAOLote;
import dao.DAOPrestadorPessoa;
import dao.DAOTelefone;
import dao.DAOTipoServico;
import model.Informacao;
import model.Lote;
import model.Pessoa;
import model.Telefone;
import model.TipoServico;

@ManagedBean
@SessionScoped
public class PrestadorPessoaMB  extends AbstractMB{
	private DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
	private DAO dao = new DAO();
	private DAOLote daol= new DAOLote();
	private DAOTelefone daot= new DAOTelefone();
	private DAOTipoServico daotipo= new DAOTipoServico();
	private DAOInformacao daoi= new DAOInformacao();
	private Pessoa prestador;
	private boolean editar;
	private boolean novo;
	private boolean novoTipoServico;
	private Telefone telefone;
	private List<TipoServico> tipos;
	private TipoServico tipoServico;	
	private List<Pessoa> prestadores;
	private List<Lote> lotes;
	private Informacao informacao;
	private Lote lote;

	
	
	public PrestadorPessoaMB() {
		System.out.println("MB nasceu!!  "+this.toString() );
		
		this.lote= new Lote();
		this.lotes = new ArrayList<Lote>();
		this.telefone= new Telefone();
		this.tipos= new ArrayList<TipoServico>();
		this.tipoServico= new TipoServico();
		this.prestador= new Pessoa();
		this.informacao= new Informacao();
		
		this.novoTipoServico= false;
		this.prestadores= new ArrayList<Pessoa>();
		getPessoas();
	}
	
	


	public Lote getLote() {
		return lote;
	}




	public void setLote(Lote lote) {
		this.lote = lote;
	}




	public Informacao getInformacao() {
		return informacao;
	}


	public List<Pessoa> getPrestadores() {
		return prestadores;
	}


	public void setPrestadores(List<Pessoa> prestadores) {
		this.prestadores = prestadores;
	}


	public List<Lote> getLotes() {
		dao.open();
		dao.begin();
		
		lotes= daol.findAll();
		dao.close();
		return lotes;
	}


	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}


	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
	}


	public Pessoa getPrestador() {
		return prestador;
	}


	public List<TipoServico> getTipos() {
		dao.open();
		dao.begin();
		tipos= daotipo.findAll();
		dao.close();
		return tipos;
	}


	public List<Pessoa> getPessoas() {
		dao.open();
		dao.begin();
		prestadores = daop.findAll();
		return prestadores;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		
		this.prestadores = pessoas;
	}


	public void setTipos(List<TipoServico> tipos) {
		this.tipos = tipos;
	}


	public boolean isNovo() {
		return novo;
	}
	

	public Telefone getTelefone() {
		return telefone;
	}


	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}


	public void setNovo(boolean novo) {
		this.novo = novo;
	}


	public void setPrestador(Pessoa prestador) {
		this.prestador = prestador;
	}
	

	public boolean isEditar() {
		return editar;
	}


	public TipoServico getTipoServico() {
		return tipoServico;
	}


	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}


	public void setEditar(boolean editar) {
		this.editar = editar;
	}


	public boolean isNovoTipoServico() {
		return novoTipoServico;
	}


	public void setNovoTipoServico(boolean novoTipoServico) {
		this.novoTipoServico = novoTipoServico;
	}
	public void getNow(){
		this.prestador.setUltimoAcesso(new GregorianCalendar().getTime());
	}


	public void resetPrestador(){
		this.prestador= new Pessoa();
	}
	public void resetTelefone(){
		this.telefone= new Telefone();
	}
	public void resetTipoServico(){
		this.tipoServico= new TipoServico();
	}
	
	public String redirectPrestadores(){
		limparPrestador();
		System.out.println("redirecionando para prestadore.xhtml");
		return "prestadores?faces-redirect=true";
	}
	public String novoPrestador(){
		System.out.println("novo prestador");
		resetPrestador();
		this.editar= false;
		this.novo= true;
		return "info-prestador";
		
		
	}
	
	public void newTipoServico(){
		System.out.println("novo tipos");
		RequestContext.getCurrentInstance().openDialog("dialogs/tipos-servico-pessoa");
		
	}
	public String novoTipoServico(){
		System.out.println("novo tipo");
		resetTipoServico();
		System.out.println(prestador.getInformacao().getCep());
		return "tipos-servico-pessoa";
		
	}

	public void salvarTelefone(){
		
//		dao.open();
//		dao.begin();
//		daot.persist(telefone);
//		daop.persist(prestador);
		prestador.addTelefone(telefone);
//		daop.merge(prestador);
//		dao.commit();
		resetTelefone();
		
		
		
	}
	public void visualizarNovoTipo(){
		System.out.println("novo tipo de servico");
		if(novoTipoServico== false){
		novoTipoServico= true;
		}else{
		
		this.novoTipoServico= false;
		}
		
		System.out.println(novoTipoServico);
	}
	
	public void adicionarTipoServico(){
		System.out.println("adicionar tipo de servico");
		dao.open();
		dao.begin();
		daotipo.persist(tipoServico);
		dao.commit();
		resetTipoServico();
		this.novoTipoServico= false;
		getTipoServico();
		
		
	}
	public void selecionarTipoServico(){
		System.out.println("selecionando tipo de servico para prestador");
		this.prestador.addTipo(tipoServico);
		tipoServico.addPrestador(prestador);
		resetTipoServico();
	}
	public void removerTipoServico() {
		System.out.println("removendo tipo de servico do prestador");
		this.prestador.removeTipo(tipoServico);
		resetTipoServico();
	}
	
	public void captura(CaptureEvent cEvent){
		ImageMB imb= new ImageMB();
		System.out.println("capturando imagem");
		ServletContext ctx= (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		String filename = imb.oncapture(ctx, cEvent);
		prestador.setFoto(filename);
		System.out.println("imagem no prestador  " + prestador.getFoto());
		
		
		
	}
	public void salvarPessoa(){
		System.out.println("Adicionando novo prestador");
		System.out.println("hora inicio  " + prestador.getHoraEntrada() );
//		dao.open();
//		dao.begin();
//		getNow();
//		
//		daotipo.merge(tipoServico);
//		prestador.setInformacao(informacao);
//		daoi.persist(informacao);
//		daop.persist(prestador);
//		
//		dao.commit();
		displayInfoMessageToUser("Prestador adicionado com sucesso!");
		this.editar= false;
		this.novo= false;
		
		
	}
	
	public void limparPrestador(){
		System.out.println("limpar prestador");
		resetPrestador();
		resetTelefone();
		resetTipoServico();
	}
	public String visualizarPrestador(){
		
		this.novo= false;
		this.editar= false;
		System.out.println(prestador.getNome());
		displayInfoMessageToUser("visualizando prestador! = " +" novo : " + novo + "editar : " + editar );
		return "info-prestador";
		
	}
	public void bloquearPrestador(){
		displayInfoMessageToUser("Prestador Bloqueado!");
	}
	public void habilitarEdicao(){
		this.editar= true;
		this.novo= false;
	}
	
}
