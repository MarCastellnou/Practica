package kiosk;
import data.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A class that represents the result in an election site
 */
public class VoteCounter {

    HashMap<Party, Integer> votos;
    int votsPartits = 0;
    int votsNull = 0;
    int votsBlanc = 0;
    ArrayList<Party> Parties;

    public VoteCounter(Set<Party> validParties) {
        votos = new HashMap<>();

        this.votsPartits=votsPartits;
        this.votsNull=votsNull;
        this.votsBlanc=votsBlanc;
        Parties.addAll(validParties);
    }
    public void countParty(Party party) throws Exception{
        if (Parties.contains(party)) {
            if (votos.containsKey(party)) {
                votos.put(party, votos.get(party) + 1);

            }else{
                votos.put(party,1);
            }
        }else{
            throw new Exception("El partido"+party+"no existe");
        }
        votsPartits++;
    }
    public void countNull() {this.votsNull++;}
    public void countBlank() {this.votsBlanc++;}
    public void scrutinize(Party party) {

        if (party.equals("")){
            countBlank();
        }else if (party.equals(null)){
            countNull();
        }else {
            try {
                countParty(party);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    public int getVotesFor(Party party) {
        if(!votos.containsKey(party)){return 0;}
        return votos.get(party);
    }
    public int getNulls() {
        return this.votsNull;
    }
    public int getBlanks() {
        return this.votsBlanc;
    }
    public int getTotal() { return this.votsPartits+this.votsBlanc+this.votsBlanc;}
}
