package test.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import main.bean.Categoria;
import main.bean.Ordine;
import main.bean.Prodotto;
import main.model.ProdottoDAO;




public class ProdottoDAOTest extends DataSourceBasedDBTestCase {
    private ProdottoDAO prodottoDAO;
    private JdbcDataSource dataSource;
    
    @Override
    protected DataSource getDataSource() {
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:test/resources/schema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("test/resources/init.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @BeforeEach
    public void setUp() throws Exception {
        // setUp del padre serve a (1) chiamare il nostro getSetUpOperation, e (2) il nostro getDataSet() per inizializzare il DB
        super.setUp();
        prodottoDAO = new ProdottoDAO(dataSource);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // tearDown del padre serve a chiamare il nostro getTearDownOperation
        super.tearDown();
    }


    @Test
    public void doRetrieveByKeyExisting()  throws SQLException {
    	int id = 883;
    	Prodotto bean = new Prodotto();
    	bean.setId(883);
    	Prodotto actual = prodottoDAO.doRetrieveByKey(id);
    	assertEquals(bean,actual);
    }
    
    @Test
    public void doRetrieveByKeyNotExisting()  throws SQLException {
    	int id = 887;
    	Prodotto bean = new Prodotto();
    	bean.setId(883);
    	Prodotto actual = prodottoDAO.doRetrieveByKey(id);
    	assertEquals(bean,actual);
    }    

    @Test
    public void doRetrieveAllExisting()  throws SQLException {
    	Collection<Prodotto> expected = new LinkedList<Prodotto>();
    	Prodotto bean = new Prodotto();
    	bean.setId(883);
    	bean.setNome("prodotto1");
    	bean.setMarchio("marchio1");
    	bean.setProduttore("prodottore1");
    	bean.setFormato("formato1");
    	bean.setDescrizione("descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1");
    	bean.setDisponibilita(100);
    	bean.setPrezzo(new BigDecimal(4));
    	expected.add(bean);
    	Collection<Prodotto> actual = prodottoDAO.doRetrieveAll("");
    	assertEquals(1,actual.size());
    	assertArrayEquals(expected.toArray(),actual.toArray());
    }
    
    @Test
    public void doRetrieveAllNotExisting()  throws SQLException {
    	Collection<Prodotto> expected = new LinkedList<Prodotto>();
    	Prodotto bean = new Prodotto();
    	bean.setId(887);
    	bean.setNome("prodotto2");
    	bean.setMarchio("marchio2");
    	bean.setProduttore("prodottore2");
    	bean.setFormato("formato2");
    	bean.setDescrizione("descrizione2");
    	bean.setDisponibilita(10);
    	bean.setPrezzo(new BigDecimal(5));
    	expected.add(bean);
    	Collection<Prodotto> actual = prodottoDAO.doRetrieveAll("");
    	assertEquals(1,actual.size());
    	assertArrayEquals(expected.toArray(), actual.toArray());
    }
    

    @Test
    public void doSaveTrue()  throws SQLException {
    	Prodotto bean = new Prodotto();
    	bean.setId(883);
    	bean.setNome("prodotto1");
    	bean.setMarchio("marchio1");
    	bean.setProduttore("prodottore1");
    	bean.setFormato("formato1");
    	bean.setDescrizione("descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1");
    	bean.setDisponibilita(100);
    	bean.setPrezzo(new BigDecimal(4));
    
    	Prodotto actual = new Prodotto();
    	actual.setId(883);
    	actual.setNome("prodotto1");
    	actual.setMarchio("marchio1");
    	actual.setProduttore("prodottore1");
    	actual.setFormato("formato1");
    	actual.setDescrizione("descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1");
    	actual.setDisponibilita(100);
    	actual.setPrezzo(new BigDecimal(4));
    	prodottoDAO.doSave(actual);
    	assertEquals(bean,actual);
    }
    
    @Test
    public void doSaveFalse()  throws SQLException {
    	Prodotto bean = new Prodotto();
    	bean.setId(887);
    	bean.setNome("prodotto2");
    	bean.setMarchio("marchio2");
    	bean.setProduttore("prodottore2");
    	bean.setFormato("formato2");
    	bean.setDescrizione("descrizione2");
    	bean.setDisponibilita(1);
    	bean.setPrezzo(new BigDecimal(4));
    
    	Prodotto actual = new Prodotto();
    	actual.setId(883);
    	actual.setNome("prodotto1");
    	actual.setMarchio("marchio1");
    	actual.setProduttore("prodottore1");
    	actual.setFormato("formato1");
    	actual.setDescrizione("descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1 descrizione1");
    	actual.setDisponibilita(100);
    	actual.setPrezzo(new BigDecimal(4));
    	prodottoDAO.doSave(actual);
    	prodottoDAO.doSave(actual);
    	assertEquals(bean,actual);
    }
    
    @Test
    public void doUpdateTrue()  throws SQLException {
    	;
    }
    
    @Test
    public void doUpdateFalse()  throws SQLException {
    	;
    }
    
    
    @Test
    public void doUpdateCategoriaTrue()  throws SQLException {
    	;
    }
    
    @Test
    public void doUpdateCategoriaFalse()  throws SQLException {
    	;
    }

    @Test
    public void doDeleteTrue()  throws SQLException {
    	;
    }
    

    @Test
    public void doRetrieveAllByCategoriaExisting()  throws SQLException {
    	;
    }
    
    @Test
    public void doRetrieveAllByCategoriaNotExisting()  throws SQLException {
    	;
    }
    
    @Test
    public void doDeleteFalse()  throws SQLException {
    	;
    }
    
    
    @Test
    public void doRicercaTrue()  throws SQLException {
    	;
    }
    
    @Test
    public void doRicercaFalse()  throws SQLException {
    	;
    }
    
    @Test
    public void doRetrieveSvincolatiTrue()  throws SQLException {
    	;
    }
    
    @Test
    public void doRetrieveSvincolatiFalse()  throws SQLException {
    	;
    } 
    
}