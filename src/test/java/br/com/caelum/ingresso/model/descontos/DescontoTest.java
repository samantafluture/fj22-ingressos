package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {
	
	private Sessao sessao;
	private Lugar lugar;

	@Before
	public void preparaSessoes() {
		this.lugar = new Lugar("A",1);
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		Sala sala = new Sala("Sala 3D", new BigDecimal("20.5"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		BigDecimal precoEsperado = new BigDecimal("32.50");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBanco() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
		BigDecimal precoEsperado = new BigDecimal("22.75");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}

}
