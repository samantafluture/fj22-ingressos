import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.model.Filme;

@RunWith(MockitoJUnitRunner.class)
public class FilmeDaoTest {

	@Mock
	private EntityManager manager;
	
	@Mock
	private TypedQuery<Filme> typedQuery;

	@InjectMocks
	private FilmeDao filmeDao = new FilmeDao();

	@Test
	public void testaEncontrarUm() {
		Filme filmeDoRambo = new Filme("Rambo", Duration.ofMinutes(100), "Ação", new BigDecimal("10.00"));
		Mockito.when(manager.find(Filme.class, 1)).thenReturn(filmeDoRambo);
		Filme filme = filmeDao.findOne(1);
		Assert.assertEquals("Rambo", filme.getNome());
	}

	//	https://www.baeldung.com/mockito-fluent-apis#deep-mocking

	@Test
	public void testaListarTodosOsFilmes() {
		
		Filme filmeDoRambo = new Filme("Rambo", Duration.ofMinutes(100), "Ação", new BigDecimal("10.00"));
		
		Mockito.when(manager.createQuery("select f from Filme f", Filme.class))
				.thenReturn(typedQuery);
		
		Mockito.when(typedQuery.getResultList()).thenReturn(Arrays.asList(filmeDoRambo));
		
		List<Filme> filmes = filmeDao.findAll();
		Assert.assertEquals(1, filmes.size());
	}
	
}
