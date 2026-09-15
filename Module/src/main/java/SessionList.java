public record SessionList(Session session, SessionList rest) {

    public static SessionList append(SessionList list, Session session){
        SessionList output = new SessionList(session, null);

        for (int i = list.length() - 1; i >= 0; i--){

            output = new SessionList(list.get(i).session , output);

        }

        return output;
    }

    public int length(){
        if (session == null)
            return 0;
        if (rest == null)
            return 1;

        return rest.length() + 1;
    }

    public SessionList get(int index){
        if (index == 0)
            return this;

        if (this.rest == null)
            return null;

        return rest.get(index - 1);
    }
}
