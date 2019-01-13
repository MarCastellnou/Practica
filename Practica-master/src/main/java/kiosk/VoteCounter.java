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
    int votsPartits;
    int votsNull;
    int votsBlanc;
    ArrayList<Party> Parties;

    public VoteCounter(Set<Party> validParties) {
        votos = new HashMap<Party, Integer>();
        Parties = new ArrayList<Party>();
        Parties.addAll(validParties);
        this.votsPartits = 0;
        this.votsNull = 0;
        this.votsBlanc = 0;

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
    public void scrutinize(Party party){

        if(party.getName().equals(" ")){
        countBlank();
        }else if(party.getName().equals("null")){
        countNull();
        }else{
        try{
        countParty(party);
        }catch(Exception exc){
        exc.printStackTrace();
        }
        }
        }
    public int getVotesFor(Party party) {
        if(votos.containsKey(party) == false){
            return 0;
        }else {
            return votos.get(party);
        }
    }
    public int getNulls() {
        return votsNull;
    }
    public int getBlanks() {
        return votsBlanc;
    }
    public int getTotal() { return votsPartits+votsBlanc+votsNull;}
}
