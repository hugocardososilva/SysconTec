package util;

import model.Funcionario;
import model.Informacao;
import model.Lote;
import model.Morador;
import model.Pessoa;
import model.Telefone;
import model.TipoServico;
import model.Usuario;
import dao.DAO;
import dao.DAOMorador;
import dao.DAOPrestadorPessoa;
import dao.DAOUser;

public class Execut {
	public static void main(String[] args) {
		Usuario admin = new Funcionario();
		admin.setCpf("1234567890");
		admin.setEmail("hugocardososilva@gmail.com");
		admin.setSenha("admin");
		admin.setLogin("admin");
		admin.setNome("Administrador");
		admin.setSobrenome("admin");
		
		
		//inserindo morador
		Lote lote = new Lote();
		Morador morador= new Morador();
		Telefone telefone= new Telefone();
		
		telefone.setDdd(83);
		telefone.setNumero((long) 88888888);
		telefone.setObservacoes("celular oi ");
		
		morador.setNome("Morador 1");
		morador.setSobrenome("Sobrenome morador 1");
		morador.setEmail("morador@morador.com");
		morador.setSenha("morador1");
		morador.setLogin("morador1");
		morador.setEstadoCivil("solteiro");
		morador.setResponsavel(true);
		morador.setCpf("012345678911");
		
		
		
		
		lote.setNumero((long) 41);
		lote.setQuadra("12");
		
		morador.addResponsabilidade(lote);
		morador.addTelefone(telefone);
		
		
	//inserindo prestador
		Informacao ip= new Informacao();
		ip.setBairro("bairro");
		ip.setCep((long) 12345678);
		ip.setCidade("cidade");
		ip.setComplemento("complemento");
		ip.setEndereco("endereco");
		ip.setEstado("PB");
		ip.setNumero((long) 13);
		ip.setWebSite("website");
		
		TipoServico ts= new TipoServico();
		ts.setDescricao("descricao");
		ts.setTipo("tipo");
		
		Telefone t2= new Telefone();
		t2.setDdd(83);
		t2.setNumero((long) 88888888);
		t2.setObservacoes("celular oi ");
		Pessoa prestador= new Pessoa();
		
		prestador.setCpf("119876543210");
		prestador.setEmail("prestador@prestador.com");
		prestador.setNome("prestador 1");
		prestador.setRg((long) 123456);
		prestador.setSobrenome("prestador sobrenome");
		prestador.setSenha("123456");
		
		
		prestador.addTelefone(t2);
		prestador.setInformacao(ip);
		prestador.setLote(lote);
		prestador.addTipo(ts);
		ts.addPrestador(prestador);
		lote.setResponsavel(morador);
		lote.addPessoa(prestador);
		
		
		DAO dao= new DAO();
		DAOUser daou= new DAOUser();
		DAOPrestadorPessoa daop= new DAOPrestadorPessoa();
		DAOMorador daom= new DAOMorador();
		
		dao.open();
		dao.begin();
		
		daou.persist(admin);
		daom.persist(morador);
		
//		daop.persist(prestador);
		
		
		dao.commit();
		System.out.println("concluido!");
		
	}

}
