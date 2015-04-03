package udo.logic.command;

import udo.util.Config;
import udo.util.Config.CommandName;

public class DisplayCommand extends Command {
    private static final String STATUS_DISP_ALL = "Displaying all tasks";
    private static final String STATUS_DISP_FREE = "Displaying free slots";
    private static final String STATUS_DISP_IMP =
            "Displaying important tasks";
    private static final String STATUS_DISP_DONE = "Displaying done tasks";

    public DisplayCommand() {
        super();
        setCommandName(CommandName.DISPLAY);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }

        if (getOption(Config.OPT_PRIO) != null) {
            setStatus(STATUS_DISP_IMP);
            updateGuiTasks(storage.query(true));
        } else if (getOption(Config.OPT_DONE) != null) {
            setStatus(STATUS_DISP_DONE);
            updateGuiTasks(storage.getDone());
        } else if (getOption(Config.OPT_FREE) != null) {
            setStatus(STATUS_DISP_FREE);
            updateGuiTasks(storage.findFreeSlots());
        } else {
            setStatus(STATUS_DISP_ALL);
            updateGuiTasks();
        }

        updateGUIStatus();
        return true;
    }
}
