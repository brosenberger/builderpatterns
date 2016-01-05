package at.bro.code.builderpattern;

import at.bro.code.builderpattern.exceptions.LegException;
import at.bro.code.builderpattern.interfaces.PetHolder;

public class Emu extends AbstractPet {

    private final String speed;

    private Emu(final EmuBuilder<?> b) {
        super(b);
        this.speed = b.speed;
    }

    public String getSpeed() {
        return speed;
    }

    public static <PETHOLDER extends PetHolder> EmuBuilder<PETHOLDER> create(String name) {
        return new EmuBuilder<>(name);
    }

    public static class EmuBuilder<PETHOLDER extends PetHolder>
            extends AbstractPet.Builder<EmuBuilder<PETHOLDER>, Emu, PETHOLDER> {
        private String speed;

        private EmuBuilder(String name) {
            super(name);
            this.legCount(2);
            this.kind(PetKind.BIRD);
        }

        public EmuBuilder<PETHOLDER> speed(String speed) {
            this.speed = speed;
            return this;
        }

        @Override
        protected void validateObject(Emu o) {
            super.validateObject(o);
            if (legCount != 2) {
                throw new LegException("An emu should have 2 legs!");
            }
            if (!PetKind.BIRD.equals(kind)) {
                throw new RuntimeException("An emu is a bird!");
            }
        }

        @Override
        protected Emu createObject() {
            return new Emu(this);
        }

    }
}
