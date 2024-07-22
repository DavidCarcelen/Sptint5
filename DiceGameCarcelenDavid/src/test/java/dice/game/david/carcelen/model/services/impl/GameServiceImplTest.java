package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.dtos.GameDTO;
import dice.game.david.carcelen.model.mappers.GameMapper;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GameServiceImplTest {

    @Mock
    private GameRepo gameRepo;

    @Mock
    private PlayerRepo playerRepo;

    @InjectMocks
    private GameServiceImpl gameService;

    private Player player;
    private Game game;
    private GameDTO gameDTO;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .id(1L)
                .email("test@example.com")
                .name("Test Player")
                .build();

        game = new Game(player.getId());
        game.setId("1");

        gameDTO = GameMapper.toDTO(game);
    }


    @Test
    void newGame_PlayerNotFound() {
        when(playerRepo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> gameService.newGame(player.getId()));
    }

    @Test
    void deleteAllGames_PlayerExists() {
        when(playerRepo.findById(anyLong())).thenReturn(Optional.of(player));

        gameService.deleteAllGames(player.getId());

        verify(gameRepo, times(1)).deleteByIdPlayer(player.getId());
    }

    @Test
    void deleteAllGames_PlayerNotFound() {
        when(playerRepo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> gameService.deleteAllGames(player.getId()));
    }

    @Test
    void getAllGames_PlayerExists() {
        when(playerRepo.findById(anyLong())).thenReturn(Optional.of(player));
        when(gameRepo.findByIdPlayer(anyLong())).thenReturn(Arrays.asList(game));

        List<GameDTO> result = gameService.getAllGames(player.getId());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(gameDTO.getId(), result.get(0).getId());
    }

    @Test
    void getAllGames_PlayerNotFound() {
        when(playerRepo.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> gameService.getAllGames(player.getId()));
    }
}

