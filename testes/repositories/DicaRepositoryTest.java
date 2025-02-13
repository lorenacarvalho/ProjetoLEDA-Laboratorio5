package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Usuario;
import entities.Dica;


public class DicaRepositoryTest {
    private DicaRepository dicaRepository;
    private Usuario usuarioBase;

    @BeforeEach
    void setUp() {
        this.dicaRepository = new DicaRepository();
        this.usuarioBase = new Usuario("Fulano", "123.456.789-00", "1234", "123456789");
    }   

    @Test
    void testAdiconaDica(){
        Dica dica = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        assertEquals(1, this.dicaRepository.adicionaDica(dica));
        Dica dica2 = new Dica(new Usuario("Ciclano", "987.654.321-00","2345", "987654321"), "MONITORIA");
        assertEquals(2, this.dicaRepository.adicionaDica(dica2));
        Dica dica3 = new Dica(new Usuario("Beltrano", "312.312.312-00", "4353", "53452532") , "PESQUISAEXTENSAO");
        assertEquals(3, this.dicaRepository.adicionaDica(dica3));
        Dica dica4 = new Dica(new Usuario("dasdadsa", "312.312.342-00", "3353", "53476532") , "PESQUISAEXTENSAO");
        assertEquals(4, this.dicaRepository.adicionaDica(dica4));
    }

    @Test
    void testListaDicasVazia() {
        String[] dicas = this.dicaRepository.listaDicas();
        assertEquals(0, dicas.length);
    }

    @Test
    void testListaDicas() {
        Dica dica1 = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        this.dicaRepository.adicionaDica(dica1);
        Dica dica2 = new Dica(new Usuario("Ciclano", "987.654.321-00", "2345", "987654321"), "MONITORIA");
        this.dicaRepository.adicionaDica(dica2);

        String[] dicas = this.dicaRepository.listaDicas();
        assertEquals(2, dicas.length);
        assertEquals(dica1.toString(), dicas[0]);
        assertEquals(dica2.toString(), dicas[1]);
    }

    @Test
    void testListaDicasDetalhesVazia() {
        String[] detalhes = this.dicaRepository.listaDicasDetalhes();
        assertEquals(0, detalhes.length);
    }

    @Test
    void testListaDicasDetalhes() {
        Dica dica1 = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        this.dicaRepository.adicionaDica(dica1);
        Dica dica2 = new Dica(new Usuario("Ciclano", "987.654.321-00", "2345", "987654321"), "MONITORIA");
        this.dicaRepository.adicionaDica(dica2);

        String[] detalhes = this.dicaRepository.listaDicasDetalhes();
        assertEquals(2, detalhes.length);
        assertEquals(dica1.exibeDetalhes(), detalhes[0]);
        assertEquals(dica2.exibeDetalhes(), detalhes[1]);
    }

    @Test
    void testListaDica() {
        Dica dica1 = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        this.dicaRepository.adicionaDica(dica1);
        assertEquals(dica1.toString(), this.dicaRepository.listaDica(1));
    }

    @Test
    void testListaDicaDetalhes() {
        Dica dica1 = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        this.dicaRepository.adicionaDica(dica1);
        assertEquals(dica1.exibeDetalhes(), this.dicaRepository.listaDicaDetalhes(1));
    }

    @Test
    void testBuscaDica() {
        Dica dica1 = new Dica(usuarioBase, "PESQUISAEXTENSAO");
        this.dicaRepository.adicionaDica(dica1);
        assertEquals(dica1, this.dicaRepository.buscaDica(1));
    }

    @Test
    void testAdicionaDicaNula() {
        assertThrows(NullPointerException.class, () -> {
            this.dicaRepository.adicionaDica(null);
        });
    }

    @Test
    void testBuscaDicaPosicaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.dicaRepository.buscaDica(1);
        });
    }
}
