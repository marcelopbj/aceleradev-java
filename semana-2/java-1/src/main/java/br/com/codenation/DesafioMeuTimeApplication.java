package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	ArrayList<Time> times = new ArrayList<>();
	ArrayList<Jogador> jogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
		if (times.stream().anyMatch(time -> time.getId().equals(id))){
			throw new IdentificadorUtilizadoException();
		}else{
			times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
		}
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws IdentificadorUtilizadoException, TimeNaoEncontradoException{
		if(jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id))){
			throw new IdentificadorUtilizadoException();
		}else if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		else{
			jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
		}
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException{
		Long idTime;
		if (!jogadores.stream().anyMatch(jogador -> jogador.getId().equals(idJogador))){
			throw new JogadorNaoEncontradoException();
		}else{
			idTime = jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador))
					.findFirst().get().getIdTime();
			times.stream().filter(time -> time.getId().equals(idTime))
					.findFirst().get().setIdCapitao(idJogador);
		}
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		if (times.stream().filter(time -> time.getId().equals(idTime))
				.findFirst().get().getIdCapitao() == null){
			throw new CapitaoNaoInformadoException();
		}
		return times.stream().filter(time -> time.getId().equals(idTime))
				.findFirst().get().getIdCapitao();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		if (!jogadores.stream().anyMatch(jogador -> jogador.getId().equals(idJogador))){
			throw new JogadorNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador))
				.findFirst().get().getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {

		if (!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		return times.stream().filter(jogador ->jogador.getId().equals(idTime))
				.findFirst().get().getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException{

		if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw  new TimeNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(Jogador::getId).collect(Collectors.toList());
	}


	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getNivelHabilidade)).get().getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getIdTime))
				.findFirst().get().getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream().map(Time::getId).collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!times.stream().anyMatch(time -> time.getId().equals(idTime))){
			throw new TimeNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId))
				.findFirst().get().getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if(!jogadores.stream().anyMatch(jogador -> jogador.getId().equals(idJogador))){
			throw new JogadorNaoEncontradoException();
		}
		return jogadores.stream().filter(jogador -> jogador.getId().equals(idJogador))
				.findFirst().get().getSalario();

	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores.stream().sorted(Comparator.comparing(Jogador::getNivelHabilidade)
				.reversed().thenComparing(Jogador::getId)).limit(top)
				.map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		if (!times.stream().anyMatch(time -> time.getId().equals(timeDaCasa)) || !times.stream().anyMatch(time -> time.getId().equals(timeDeFora))) {
			throw new TimeNaoEncontradoException();
		}
		String uniformeTimeCasa = times.stream().filter(time -> time.getId().equals(timeDaCasa))
				.findFirst().get().getCorUniformePrincipal();
		String uniformeTimeFora = times.stream().filter(time -> time.getId().equals(timeDeFora))
				.findFirst().get().getCorUniformePrincipal();
		if(uniformeTimeCasa.equals(uniformeTimeFora)){
			return times.stream().filter(time -> time.getId().equals(timeDeFora))
					.findFirst().get().getCorUniformeSecundario();
		}else{
			return uniformeTimeFora;
		}

	}

}
