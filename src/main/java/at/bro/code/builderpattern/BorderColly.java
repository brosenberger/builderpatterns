package at.bro.code.builderpattern;

import at.bro.code.builderpattern.exceptions.LegException;
import at.bro.code.builderpattern.interfaces.PetHolder;

public class BorderColly extends AbstractPet {

    private final String toy;

    private BorderColly(final BorderCollyBuilder<?> b) {
        super(b);
        this.toy = b.toy;
    }

    public String getToy() {
        return toy;
    }

    public static <PETHOLDER extends PetHolder> BorderCollyBuilder<PETHOLDER> create(String name) {
        return new BorderCollyBuilder<>(name);
    }

    public static class BorderCollyBuilder<PETHOLDER extends PetHolder>
            extends AbstractPet.Builder<BorderCollyBuilder<PETHOLDER>, BorderColly, PETHOLDER> {
        private String toy;

        private BorderCollyBuilder(String name) {
            super(name);
            this.legCount(4);
            this.kind(PetKind.DOG);
        }

        public BorderCollyBuilder<PETHOLDER> toy(String toy) {
            this.toy = toy;
            return this;
        }

        @Override
        protected void validateObject(BorderColly o) {
            super.validateObject(o);
            if (legCount != 4) {
                throw new LegException("A Border colly should have 4 legs!");
            }
            if (!PetKind.DOG.equals(kind)) {
                throw new RuntimeException("A Border colly is a dog!");
            }
        }

        @Override
        protected BorderColly createObject() {
            return new BorderColly(this);
        }

    }
}
