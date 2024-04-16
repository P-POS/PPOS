package sale;

public class MemberDTO {

    String clientName;
    int clientId;
    int pointScore;

    public MemberDTO(String clientName, int clientId, int pointScore) {

        this.clientName = clientName;
        this.clientId = clientId;
        this.pointScore = pointScore;
    }

    public String getClientName() {
        return clientName;
    }

    public int getPointScore() {
        return pointScore;
    }

    public int getClientId() {
        return clientId;
    }
}
