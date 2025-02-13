package repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Usuario;

public class UsuarioRepositoryTest {

    private UsuarioRepository usuarioRepository;
    
    @BeforeEach
    void setUp() {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    @Test
    void testAdicionaEstudante() {
        assertTrue(this.usuarioRepository.adicionaEstudante(new Usuario("Fulano", "123.456.789-00", "1234", "123456789")));
    }
    
    @Test
    void testAdicionaEstudanteNulo() {
        String mensagem = assertThrows(NullPointerException.class, () -> {
            this.usuarioRepository.adicionaEstudante(null);
            }).getMessage();
        assertEquals("O usuário não pode ser nulo", mensagem);
    }
    
    @Test
    void testAdicionaEstudanteDuplicado() {
        this.usuarioRepository.adicionaEstudante(new Usuario("Fulano", "123.456.789-00", "1234", "123456789"));
        assertFalse(this.usuarioRepository.adicionaEstudante(new Usuario("Fulano", "123.456.789-00", "1234", "123456789")));
    }
    
    @Test
    void testListaEstudantes() {
        this.usuarioRepository.adicionaEstudante(new Usuario("Fulano", "123.456.789-00", "1234", "123456789"));
        String[] lista = this.usuarioRepository.listaEstudantes();
        assertEquals("Nome: Fulano, matrícula 123456789", lista[0]);
    }
    
    @Test
    void testListaEstudantesVazio() {
        String[] lista = this.usuarioRepository.listaEstudantes();
        assertEquals(0, lista.length);
    }
    
    @Test
    void testListaEstudantesOrdenado() {
        this.usuarioRepository.adicionaEstudante(new Usuario("Fulano", "123.456.789-00", "1234", "123456789"));
        this.usuarioRepository.adicionaEstudante(new Usuario("Beltrano", "987.654.321-00", "4321", "987654321"));
        String[] lista = this.usuarioRepository.listaEstudantes();
        assertEquals("Nome: Beltrano, matrícula 987654321", lista[0]);
        assertEquals("Nome: Fulano, matrícula 123456789", lista[1]);
    }
    
    @Test
    void testListaEstudantesRankingDicas() {
        Usuario usuario1 = new Usuario("Fulano", "123.456.789-00", "1234", "123456789");
        this.usuarioRepository.adicionaEstudante(usuario1);
        usuario1.recebeBonificacao(10);
       
        Usuario usuario2 = new Usuario("Beltrano", "987.654.321-00", "4321", "987654321");
        this.usuarioRepository.adicionaEstudante(usuario2);
        usuario2.recebeBonificacao(20);
       
        Usuario usuario3 = new Usuario("Ciclano", "123.456.789-01", "1234", "123456987");
        this.usuarioRepository.adicionaEstudante(usuario3);
        usuario3.recebeBonificacao(15);
       
        String[] lista = this.usuarioRepository.listaEstudantesRankingDicas();
        assertEquals("Nome: Beltrano, matrícula 987654321", lista[0]);
        assertEquals("Nome: Ciclano, matrícula 123456987", lista[1]);
        assertEquals("Nome: Fulano, matrícula 123456789", lista[2]);
    }
}