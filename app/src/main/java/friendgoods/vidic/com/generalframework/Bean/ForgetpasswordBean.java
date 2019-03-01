package friendgoods.vidic.com.generalframework.Bean;

public class ForgetpasswordBean {
    @Override
    public String toString() {
        return "ForgetPassword{" +
                "messages='" + messages + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String messages;
    private String state;
}
