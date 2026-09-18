public record SessionList(Session session, SessionList rest) {

    /** Increments the given session by 1 participant */
    public static SessionList incrementSession(SessionList list, int id){
        if (list == null || list.session() == null) {
            return null; // No matching session
        }

        int len = list.length();
        int index = -1;
        for (int i = 0; i < len; i++){
            if (list.get(i).session().id() == id){
                index = i;
                break;
            }
        }

        if (index == -1)
            return null;

        Session targetSession = list.get(index).session();
        if (targetSession.curPart() >= targetSession.maxPart()) {
            return null; // Session is full
        }

        // REMOVED MainGUI.removeSession(id) - we don't need it!
        // We just rebuild the list skipping the old node and inserting the new one.
        SessionList output = null;

        for (int i = len - 1; i >= 0; i--){
            if (i == index) {
                Session temp = list.get(i).session();
                output = new SessionList(
                        new Session(temp.id(), temp.title(), temp.mentor(), temp.date(), temp.loc(), temp.curPart() + 1, temp.maxPart()), output
                );
            } else {
                output = new SessionList(list.get(i).session, output);
            }
        }

        return output;
    }

    public static SessionList getSession(SessionList list, int id){
        switch(list) {
            case null:
                return null;
            case SessionList(Session s,SessionList r):
                if(s.id() == id)
                    return new SessionList(s, null);
                else {
                    return getSession(r,id);
                }
        }
        /*
        if (list.session == null)
            return null;

         if (list.session.id() == id)
            return



         */

        /*if (list == null) {
            return null;
        }

        else if (list.session.id() == id) {
            return new SessionList(list.session, null);
        }

        else {
            return getSession(list.rest, id);
        }*/
    }

    public static SessionList getSession(SessionList list, String mentor){

        switch(list) {
            case null:
                return null;
            case SessionList(Session s,SessionList r):
                if(s.mentor().equals(mentor))
                    return new SessionList(s, null);
                else {
                    return getSession(r,mentor);
                }
        }

        /*if (list == null) {
            return null;
        }
        else if (list.session.mentor().equals(mentor)) {
            return new SessionList(list.session, getSession(list.rest, mentor));
        }
        else {
            return getSession(list.rest, mentor);
        }*/
    }

    /** returns a SessionList sorted by date */
    public static SessionList sortByDate(SessionList list){

        // Base case (only one or no elements in the list)
        if (list == null || list.rest == null)
            return list;

        int len = list.length();
        int mid = len / 2;

        SessionList left = splitLeft(list, mid);
        SessionList right = splitRight(list, mid);

        left = sortByDate(left);
        right = sortByDate(right);

        return sortedMerge(left, right);
    }

    private static SessionList splitRight(SessionList list, int mid){
        return list.get(mid);
    }

    private static SessionList splitLeft(SessionList list, int mid){
        SessionList output = null;

        for (int i = mid - 1; i >= 0; i--){
            output = new SessionList(list.get(i).session, output);
        }

        return output;
    }

    private static SessionList sortedMerge(SessionList a, SessionList b){
        if (a == null)
            return b;
        if (b == null)
            return a;

        String dateA = a.session.date();
        String dateB = b.session.date();

        if (dateA.compareToIgnoreCase(dateB) <= 0)
            return new SessionList(a.session, sortedMerge(a.rest, b));
        else
            return new SessionList(b.session, sortedMerge(a, b.rest));
    }


    /** adds a session to the end */
    public static SessionList append(SessionList list, Session newSession){
        SessionList output = new SessionList(newSession, null);

        for (int i = list.length() - 1; i >= 0; i--){

            output = new SessionList(list.get(i).session , output);

        }

        return output;
    }

    /** removes a session by ID if it is present, returns null if nothing was removed */
    public static SessionList remove(SessionList list, int id){
        if (list == null || list.session() == null) return null; // Nothing to remove

        int len = list.length();
        int index = -1;
        for (int i = 0; i < len; i++){
            if (list.get(i).session().id() == id){
                index = i;
                break;
            }
        }

        if (index == -1)
            return null;

        SessionList output = (index + 1 < len) ? list.get(index + 1) : null;

        for (int i = index - 1; i >= 0; i--){
            output = new SessionList(list.get(i).session, output);
        }

        // If list is completely empty now, return your empty list format instead of null
        return (output == null) ? new SessionList(null, null) : output;
    }

    /** Returns the length of the given SessionList */
    public int length(){
        if (session == null)
            return 0;
        if (rest == null)
            return 1;

        int length = 0;

        return rest.length() + 1;
    }

    /** Gets a SessionList at the given index, returns null if out of bounds */
    public SessionList get(int index){
        if (index == 0)
            return this;

        if (this.rest == null)
            return null;

        return rest.get(index - 1);
    }
}
