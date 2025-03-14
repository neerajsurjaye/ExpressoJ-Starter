package com.spec.services;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spec.db.NotesDB;
import com.spec.models.Note;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotesService {

    private NotesService() {
        // pass
    }

    public static String getNotesAsJsonString(String body) {

        JsonElement jsonElement = JsonParser.parseString(body);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String userName = jsonObject.get("username").getAsString();

        log.error(userName);
        log.error(NotesDB.getDbAsString());
        List<Note> userNotes = NotesDB.getData(userName);

        return new Gson().toJson(userNotes);

    }

    public static String addNote(String body) {

        JsonElement jsonElement = JsonParser.parseString(body);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement notejsonElement = jsonObject.get("note");
        String userName = jsonObject.get("username").getAsString();

        Note note = new Gson().fromJson(notejsonElement, Note.class);

        NotesDB.putData(userName, note);

        return notejsonElement.toString();

    }

    public static String updateNote(String body) {
        JsonElement jsonElement = JsonParser.parseString(body);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String username = jsonObject.get("username").getAsString();
        String uuid = jsonObject.get("uuid").getAsString();

        Note noteToUpdate = null;
        List<Note> userNotes = NotesDB.getData(username);

        for (Note x : userNotes) {
            if (x.getUuid().equals(uuid)) {
                noteToUpdate = x;
            }
        }

        if (noteToUpdate == null) {
            return "";
        }

        if (jsonObject.has("title")) {
            noteToUpdate.setTitle(jsonObject.get("title").getAsString());
        }
        if (jsonObject.has("body")) {
            noteToUpdate.setBody(jsonObject.get("body").getAsString());
        }

        return new Gson().toJson(noteToUpdate);

    }

    public static String deleteNote(String body) {
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();

        String username = jsonObject.get("username").getAsString();
        String uuid = jsonObject.get("uuid").getAsString();

        List<Note> userNotes = NotesDB.getData(username);

        Note delNote = null;
        int idx = -1;
        for (int i = 0; i < userNotes.size(); i++) {
            if (userNotes.get(i).getUuid().equals(uuid)) {
                idx = i;
                break;
            }
        }

        if (idx != -1) {
            delNote = userNotes.remove(idx);
        }

        return new Gson().toJson(delNote);
    }

}
