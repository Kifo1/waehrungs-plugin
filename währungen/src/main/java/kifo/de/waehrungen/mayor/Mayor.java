package kifo.de.waehrungen.mayor;

import kifo.de.waehrungen.Main;
import kifo.de.waehrungen.utils.Config;

import java.util.UUID;

public class Mayor {

    private UUID uuid;

    public Mayor() {
        Config config = Main.getInstance().getConfiguration();

        String s = config.getConfig().getString("mayor");

        if(s != null) {
            this.uuid = UUID.fromString(s);
        }
    }

    public void save() {
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("mayor", uuid.toString());
    }

    public void setMayor(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getMayor() {
        return this.uuid;
    }

}
