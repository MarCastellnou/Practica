package kiosk;

import data.DigitalSignature;
import data.MailAddress;
import data.Party;
import services.ElectoralOrganism;
import services.MailerService;

/**
 * Implements a simplification of Use Case: Emit eVote
 */
public class VotingKiosk {

    ElectoralOrganism eO;
    MailerService mService;
    Party party;
    MailAddress address;
    public VoteCounter count;

    public VotingKiosk() {
        this.eO=null;
        this.mService=null;
        this.party=null;
        this.address=null;
    }

    public void setElectoralOrganism(ElectoralOrganism eO) {this.eO=eO;}
    public void setMailerService(MailerService mService){this.mService=mService;}
    public void vote(Party party) {
        this.party=party;
        count.scrutinize(party);
    }
    public void sendeReceipt(MailAddress address) {
        this.address=address;
        DigitalSignature sign = eO.askForDigitalSignature(party);
        mService.send(address,sign);
    }
}
