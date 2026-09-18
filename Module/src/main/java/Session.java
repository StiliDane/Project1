public record Session(int id, String title, String mentor, String date, String loc, int curPart, int maxPart) {
    @Override
    public String toString() {
        return "<b>ID:</b> " + id +
                " | <b>Title:</b> " + title +
                " | <b>Mentor:</b> " + mentor +
                "<br><b>Date:</b> " + date +
                " | <b>Location:</b> " + loc +
                " | <b>Participants:</b> " + curPart + "/" + maxPart;
    }
}