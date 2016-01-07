package at.bro.code.builderpattern;

import at.bro.code.builderpattern.BorderColly.BorderCollyBuilder;
import at.bro.code.builderpattern.exceptions.NoPetException;
import at.bro.code.builderpattern.interfaces.Person;
import at.bro.code.builderpattern.interfaces.Pet;
import at.bro.code.builderpattern.interfaces.PetHolder;

public class PetLover implements Person {
    private final String name;
    private final int age;
    private final Pet pet;

    protected PetLover(PetLoverBuilder b) {
        this.name = b.name;
        this.age = b.age;
        this.pet = b.pet;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public Pet getPet() {
        return pet;
    }

    public static PetStep create(String name) {
        return new PetLoverBuilder(name);
    }

    public static class PetLoverBuilder implements PetHolder, PetStep, BuildAndAgeStep {
        private final String name;
        private int age;
        private Pet pet;

        private PetLoverBuilder(String name) {
            this.name = name;
        }

        @Override
        public PetLoverBuilder age(int age) {
            this.age = age;
            return this;
        }

        @Override
        public BorderCollyBuilder<BuildAndAgeStep> borderColly(String name) {
            return pet(BorderColly.create(name));
        }

        @Override
        public <PETBUILDER extends AbstractPet.Builder<?, PET, BuildAndAgeStep>, PET extends AbstractPet> PETBUILDER pet(
                PETBUILDER petBuilder) {
            petBuilder.setParent(this);
            return petBuilder;
        }

        @Override
        public void setPet(AbstractPet pet) {
            this.pet = pet;
        }

        @Override
        public PetLover build() {
            PetLover person;
            if (this.age > 65) {
                person = new SeniorPetLover(this);
            } else {
                person = new PetLover(this);
            }
            if (person.getPet() == null) {
                throw new NoPetException("Everyone must have pet!");
            }
            return person;
        }
    }

    public static interface PetStep {
        BorderCollyBuilder<BuildAndAgeStep> borderColly(String name);

        <PETBUILDER extends AbstractPet.Builder<?, PET, BuildAndAgeStep>, PET extends AbstractPet> PETBUILDER pet(
                PETBUILDER petBuilder);
    }

    public static interface BuildAndAgeStep extends PetHolder, AgeStep, BuildStep {

    }

    public static interface AgeStep {
        BuildStep age(int age);
    }

    public static interface BuildStep {
        public PetLover build();
    }
}
