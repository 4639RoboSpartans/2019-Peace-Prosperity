package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class InjectedSubsystem extends Subsystem {
    protected InjectedSubsystem() {
        super();
    }

    protected InjectedSubsystem(String name) {
        super(name);
    }

    @Override
    public final void setDefaultCommand(Command command) {
        super.setDefaultCommand(command);
    }

    @Override
    protected final void initDefaultCommand() {
        
    }
}