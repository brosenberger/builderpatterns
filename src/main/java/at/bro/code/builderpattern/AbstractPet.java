package at.bro.code.builderpattern;

import at.bro.code.builderpattern.interfaces.ParentBuilder;
import at.bro.code.builderpattern.interfaces.Pet;
import at.bro.code.builderpattern.interfaces.PetHolder;

public abstract class AbstractPet implements Pet {

    private final String name;
    private final PetKind kind;
    private final int legCount;

    protected AbstractPet(Builder<?, ?, ?> b) {
        name = b.name;
        kind = b.kind;
        legCount = b.legCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PetKind getKind() {
        return kind;
    }

    @Override
    public int getLegCount() {
        return legCount;
    }

    protected static abstract class Builder<EXTENSION extends Builder<?, OBJECT, PARENT>, OBJECT extends AbstractPet, PARENT extends PetHolder>
            implements ParentBuilder<PARENT, OBJECT> {
        protected final String name;
        protected PetKind kind;
        protected int legCount = 0;
        private PARENT parentBuilder;

        protected Builder(String name) {
            this.name = name;
        }

        public Builder<EXTENSION, OBJECT, PARENT> kind(PetKind kind) {
            this.kind = kind;
            return this;
        }

        public Builder<EXTENSION, OBJECT, PARENT> legCount(int legCount) {
            this.legCount = legCount;
            return this;
        }

        @Override
        public void setParent(PARENT parent) {
            this.parentBuilder = parent;
        }

        protected abstract OBJECT createObject();

        protected void validateObject(OBJECT o) {
            if (o.getKind() == null) {
                throw new RuntimeException("The pet must be of some kind!");
            }
        }

        @Override
        public PARENT build() {
            final OBJECT o = createObject();
            validateObject(o);
            parentBuilder.setPet(o);
            return parentBuilder;
        }

    }

}
