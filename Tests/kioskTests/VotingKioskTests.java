package kioskTests;
import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.MailerService;
import java.util.ArrayList;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VotingKioskTests {
    HashSet<Party> parties;
    ElectoralOrganism eO;
    MailerService mService;
    VotingKiosk kiosk;

    public static class eOTestDoble implements ElectoralOrganism{
        ArrayList<Nif> votantes = new ArrayList<>(); //Ya han votado
        ArrayList<Nif> censoElectoral = new ArrayList<>(); //Se encuentran en el censo

        @Override
        public boolean canVote(Nif nif) {
            if(!votantes.contains(nif) && censoElectoral.contains(nif)){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void disableVoter(Nif nif) {
            votantes.add(nif);
        }

        @Override
        public DigitalSignature askForDigitalSignature(Party party) {
            byte[] sign = party.getName().getBytes();
            DigitalSignature ds = new DigitalSignature(sign);
            return ds;
        }
    }

    public static class mailServiceTestDoble implements MailerService{
        int enviados = 0;
        @Override
        public void send(MailAddress address, DigitalSignature signature) {
            enviados++;
        }
    }


    @BeforeEach
    public void BeforeEach(){
        parties = new HashSet<>();
        Party partido = new Party("p");
        Party partido1 = new Party("p1");
        Party partido2 = new Party("p2");
        Party partido3 = new Party("p3");
        Party partido4 = new Party("p4");
        parties.add(partido);
        parties.add(partido1);
        parties.add(partido2);
        parties.add(partido3);
        parties.add(partido4);
        kiosk = new VotingKiosk();
        kiosk.count = new VoteCounter(parties);
        eO = new eOTestDoble();
        mService = new mailServiceTestDoble();
        Nif nif = new Nif("73214187N");
        Nif nif1 = new Nif("63925698L");
        Nif nif2 = new Nif("25968741M");
        Nif nif3 = new Nif("23056817D");
        ((eOTestDoble) eO).censoElectoral.add(nif);
        ((eOTestDoble) eO).censoElectoral.add(nif1);
        ((eOTestDoble) eO).censoElectoral.add(nif2);
        ((eOTestDoble) eO).censoElectoral.add(nif3);
    }

    @Test
    public void voteTest(){
        Party party = new Party("p");
        Party party1 = new Party("p3");
        Party party2 = new Party("p2");
        Party party3 = new Party("p4");
        kiosk.vote(party);
        kiosk.vote(party);
        kiosk.vote(party1);
        kiosk.vote(party1);
        kiosk.vote(party1);
        kiosk.vote(party2);
        assertEquals(2,kiosk.count.getVotesFor(party));
        assertEquals(3,kiosk.count.getVotesFor(party1));
        assertEquals(1,kiosk.count.getVotesFor(party2));
        assertEquals(0,kiosk.count.getVotesFor(party3));
    }

    @Test
    public void sendeReceiptTest(){
        kiosk.setElectoralOrganism(eO);
        kiosk.setMailerService(mService);

        Nif nif = new Nif("73214187N");
        Party party = new Party("p");
        MailAddress adress= new MailAddress("mcq2@estudiants.udl");

        if(eO.canVote(nif)){
            kiosk.vote(party);
            eO.disableVoter(nif);
            kiosk.sendeReceipt(adress);
        }
        assertEquals(1,((mailServiceTestDoble) mService).enviados);
    }

}
