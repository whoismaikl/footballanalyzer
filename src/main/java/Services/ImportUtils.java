package Services;

import Database.hibernateORM.GameDAOImpl;
import Models.Domain.Game;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImportUtils {

    public void listFilesForFolder(final File folder) throws IOException {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(folder + "\\" + fileEntry.getName());
                readXmlFile(folder + "\\" + fileEntry.getName());
            }
        }
    }

    private void readXmlFile(String filePath) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            StringBuffer buff = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                buff.append(line);
            }

            XmlMapper mapper = new XmlMapper();
            Game game = mapper.readValue(buff.toString(), Game.class);
            saveGame(game);
        } catch (NullPointerException e) {
            System.out.println("NPE");
        } catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("GE");
        }
    }

    private void saveGame(Game game) {
        SaveUtils saveLogic = new SaveUtils();

        GameDAOImpl gameDaoImpl = new GameDAOImpl();
        if (!gameDaoImpl.checkGameExistence(game)) {
            saveLogic.saveTeams(game);
            saveLogic.savePlayers(game.getTeams().get(0), game.getTeams().get(1));
            saveLogic.saveReferees(game, game.getTeams().get(0), game.getTeams().get(1));
            saveLogic.saveGame(game);
        } else {
            System.out.println("Game already exists. " + game.toString());
        }
    }
}
