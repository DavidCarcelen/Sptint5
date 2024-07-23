package dice.game.david.carcelen.model.services.impl;

import dice.game.david.carcelen.exceptions.IdNotFoundException;
import dice.game.david.carcelen.exceptions.PlayerNotFoundException;
import dice.game.david.carcelen.model.domain.Game;
import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.domain.Role;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.repository.mongo.GameRepo;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepo playerRepo;

    @Mock
    private GameRepo gameRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;
    private PlayerDTO playerDTO;
    private List<Game> games;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .id(1)
                .email("test@example.com")
                .name("Test Player")
                .role(Role.USER)
                .signDate(new Date())
                .build();

        playerDTO = new PlayerDTO();
        playerDTO.setId(1);
        playerDTO.setEmail("test@example.com");
        playerDTO.setName("Updated Player");

        // Create games for win rate calculation
        games = new ArrayList<>();
        Game game1 = new Game();
        game1.setId("1");
        game1.setIdPlayer(1);
        game1.setValueDie1(3);
        game1.setValueDie2(4);
        game1.setWin(true);
        games.add(game1);

        Game game2 = new Game();
        game2.setId("2");
        game2.setIdPlayer(1);
        game2.setValueDie1(2);
        game2.setValueDie2(3);
        game2.setWin(false);
        games.add(game2);

        Game game3 = new Game();
        game3.setId("3");
        game3.setIdPlayer(1);
        game3.setValueDie1(5);
        game3.setValueDie2(2);
        game3.setWin(true);
        games.add(game3);
    }

    @Test
    void createAdminUserIfNotExists() {
        when(playerRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        playerService.createAdminUserIfNotExists();

        verify(playerRepo, times(1)).save(any(Player.class));
    }

    @Test
    void updatePlayer() {
        when(playerRepo.findByEmail(anyString())).thenReturn(Optional.of(player));

        playerService.updatePlayer(playerDTO.getId(), playerDTO);

        verify(playerRepo, times(1)).save(any(Player.class));
    }

    @Test
    void updatePlayer_PlayerNotFound() {
        when(playerRepo.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> {
            playerService.updatePlayer(playerDTO.getId(), playerDTO);
        });
    }

    @Test
    void deletePlayer() {
        when(playerRepo.existsById(anyLong())).thenReturn(true);

        playerService.deletePlayer(1);

        verify(playerRepo, times(1)).deleteById(anyLong());
    }

    @Test
    void deletePlayer_IdNotFound() {
        when(playerRepo.existsById(anyLong())).thenReturn(false);

        assertThrows(IdNotFoundException.class, () -> {
            playerService.deletePlayer(1);
        });
    }

    @Test
    void getAllPlayers() {
        List<Player> players = Arrays.asList(player);
        when(playerRepo.findAll()).thenReturn(players);
        when(gameRepo.findByIdPlayer(anyLong())).thenReturn(games);

        List<PlayerDTO> result = playerService.getAllPlayers();

        assertFalse(result.isEmpty());
    }


    @Test
    void getWinner() {
        playerDTO.setWinRate(games);
        List<PlayerDTO> players = Arrays.asList(playerDTO);

        when(gameRepo.findByIdPlayer(anyLong())).thenReturn(games);

        PlayerDTO result = playerService.getWinner(players);

        assertNotNull(result);
        assertEquals(playerDTO.getEmail(), result.getEmail());
    }

    @Test
    void getLoser() {
        games = Arrays.asList(
                new Game() {{
                    setId("4");
                    setIdPlayer(1);
                    setValueDie1(1);
                    setValueDie2(1);
                    setWin(false);
                }},
                new Game() {{
                    setId("5");
                    setIdPlayer(1);
                    setValueDie1(2);
                    setValueDie2(2);
                    setWin(false);
                }},
                new Game() {{
                    setId("6");
                    setIdPlayer(1);
                    setValueDie1(3);
                    setValueDie2(3);
                    setWin(false);
                }}
        );
        playerDTO.setWinRate(games);
        List<PlayerDTO> players = Arrays.asList(playerDTO);

        when(gameRepo.findByIdPlayer(anyLong())).thenReturn(games);

        PlayerDTO result = playerService.getLoser(players);

        assertNotNull(result);
        assertEquals(playerDTO.getEmail(), result.getEmail());
    }
}
