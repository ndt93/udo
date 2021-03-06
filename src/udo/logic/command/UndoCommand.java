package udo.logic.command;

import udo.logic.Logic;
import udo.util.Config.CommandName;

//@author A0093587M
public class UndoCommand extends Command {
    private static final String STATUS_UNDO_COMPLETED = "Undo completed";
    private static final String STATUS_NO_UNDO = "no more operation to undo";

    public UndoCommand() {
        super();
        setCommandName(CommandName.UNDO);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }

        boolean isSuccessful = storage.undo();

        if (!isSuccessful) {
            setStatus(Logic.formatErrorStr(STATUS_NO_UNDO));
        } else {
            setStatus(STATUS_UNDO_COMPLETED);
            updateGuiTasks(storage.query());
            updateReminder();
        }

        updateGUIStatus();
        return isSuccessful;
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

}
