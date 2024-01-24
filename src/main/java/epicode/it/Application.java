package epicode.it;

import epicode.it.DAO.EventoDAO;
import epicode.it.DAO.LocationDAO;
import epicode.it.DAO.PartecipazioneDAO;
import epicode.it.DAO.PersonaDAO;
import epicode.it.Entities.Evento;
import epicode.it.Entities.Location;
import epicode.it.Entities.Partecipazione;
import epicode.it.Entities.Persona;
import epicode.it.Enums.Genere;
import epicode.it.Enums.StatoPartecipazione;
import epicode.it.Enums.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

import static epicode.it.Utils.Jpa.getEntityManagerFactory;

public class Application {
    private static final EntityManagerFactory emf = getEntityManagerFactory();


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        try {
            LocationDAO locationDAO = new LocationDAO(em);
            EventoDAO eventoDAO = new EventoDAO(em);
            PersonaDAO personaDAO = new PersonaDAO(em);
            PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

            Location location = new Location("Momò", "Roma");
            LocalDate dataNascita = LocalDate.of(2000, 12, 10);
            Persona AnnaRossi = new Persona("Anna", "Rossi", "annarossi@gmail.com", dataNascita, Genere.FEMMINA);

            LocalDate dataEvento = LocalDate.of(2024, 3, 20);
            Evento evento = new Evento("Salvador Dalì", "Mostra delle opere più emozionanti", TipoEvento.PUBBLICO, 10000, location);

            Partecipazione partecipazione = new Partecipazione(AnnaRossi, evento, StatoPartecipazione.CONFERMATA);
            personaDAO.save(AnnaRossi);
            locationDAO.save(location);
            eventoDAO.save(evento);
            partecipazioneDAO.save(partecipazione);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

    }
}