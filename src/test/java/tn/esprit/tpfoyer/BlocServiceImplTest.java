package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlocServiceImplTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllBlocs() {
        List<Bloc> blocs = new ArrayList<>();
        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveAllBlocs();
        assertEquals(blocs, result);
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testAddBloc() {
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.addBloc(bloc);
        assertEquals(bloc, result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testModifyBloc() {
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.modifyBloc(bloc);
        assertEquals(bloc, result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void testRemoveBloc() {
        Long blocId = 1L;

        blocService.removeBloc(blocId);
        verify(blocRepository, times(1)).deleteById(blocId);
    }

    @Test
    void testRetrieveBloc() {
        Long blocId = 1L;
        Bloc bloc = new Bloc();
        when(blocRepository.findById(blocId)).thenReturn(Optional.of(bloc));

        Bloc result = blocService.retrieveBloc(blocId);
        assertEquals(bloc, result);
        verify(blocRepository, times(1)).findById(blocId);
    }

    @Test
    void testRetrieveBlocsSelonCapacite() {
        List<Bloc> blocs = List.of(
                new Bloc(1L, "Bloc A", 50, null, new HashSet<>()),
                new Bloc(2L, "Bloc B", 70, null, new HashSet<>())
        );
        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(60);
        assertEquals(1, result.size());
        assertEquals("Bloc B", result.get(0).getNomBloc());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void testFindBlocsSansFoyer() {
        List<Bloc> blocs = new ArrayList<>();
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsSansFoyer();
        assertEquals(blocs, result);
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    void testFindBlocsByNomEtCapacite() {
        String nomBloc = "Bloc A";
        long capacite = 100;
        List<Bloc> blocs = List.of(new Bloc(1L, nomBloc, capacite, null, new HashSet<>()));
        when(blocRepository.findAllByNomBlocAndCapaciteBloc(nomBloc, capacite)).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsParNomEtCap(nomBloc, capacite);
        assertEquals(blocs, result);
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc(nomBloc, capacite);
    }
}
