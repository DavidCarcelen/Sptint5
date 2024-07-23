package dice.game.david.carcelen.controllers;

import dice.game.david.carcelen.model.domain.Player;
import dice.game.david.carcelen.model.domain.Role;
import dice.game.david.carcelen.model.dtos.PlayerDTO;
import dice.game.david.carcelen.model.repository.jpa.PlayerRepo;
import dice.game.david.carcelen.model.services.JWTService;
import dice.game.david.carcelen.model.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private PlayerRepo playerRepo;

    private Player player;
    private PlayerDTO playerDTO;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .id(1)
                .email("test@example.com")
                .name("Test Player")
                .password("password")
                .role(Role.USER)
                .signDate(new Date())
                .build();

        playerDTO = new PlayerDTO();
        playerDTO.setId(1);
        playerDTO.setEmail("test@example.com");
        playerDTO.setName("Updated Player");

        when(playerRepo.findById(anyLong())).thenReturn(Optional.of(player));
        when(jwtService.getUserName(anyString())).thenReturn(player.getEmail());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void updatePlayer() throws Exception {
        String token = "Bearer token";
        mockMvc.perform(put("/players/users/{idPlayer}", 1)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"email\":\"test@example.com\",\"name\":\"Updated Player\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("player updated"));

        verify(jwtService, times(1)).checkId(anyLong(), anyString());
        verify(playerService, times(1)).updatePlayer(1,any(PlayerDTO.class));

    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void deletePlayer() throws Exception {
        String token = "Bearer token";
        mockMvc.perform(delete("/players/users/delete/{idPlayer}", 1)
                        .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andExpect(content().string("Player deleted"));

        verify(jwtService, times(1)).checkId(anyLong(), anyString());
        verify(playerService, times(1)).deletePlayer(anyLong());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllPlayers() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(Arrays.asList(playerDTO));

        mockMvc.perform(get("/players/admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is(playerDTO.getEmail())))
                .andExpect(jsonPath("$[0].name", is(playerDTO.getName())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAverageRate() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(Arrays.asList(playerDTO));
        when(playerService.getAverageRate(Mockito.anyList())).thenReturn("Average Win Rate: 50.00");

        mockMvc.perform(get("/players/admin/getAverageRate"))
                .andExpect(status().isOk())
                .andExpect(content().string("Average Win Rate: 50.00"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getWinner() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(Arrays.asList(playerDTO));
        when(playerService.getWinner(Mockito.anyList())).thenReturn(playerDTO);

        mockMvc.perform(get("/players/users/ranking/winner"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(playerDTO.getEmail())))
                .andExpect(jsonPath("$.name", is(playerDTO.getName())));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getLoser() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(Arrays.asList(playerDTO));
        when(playerService.getLoser(Mockito.anyList())).thenReturn(playerDTO);

        mockMvc.perform(get("/players/users/ranking/loser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(playerDTO.getEmail())))
                .andExpect(jsonPath("$.name", is(playerDTO.getName())));
    }
}

