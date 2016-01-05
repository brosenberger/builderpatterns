package at.bro.code.builderpattern;

import at.bro.code.builderpattern.exceptions.NoPetException;
import at.bro.code.builderpattern.interfaces.Person;
import at.bro.code.builderpattern.interfaces.Pet;
import at.bro.code.builderpattern.interfaces.PetHolder;

public class NormalPerson implements Person {
    private final String name;
    private final int age;
    private final Pet pet;

    private NormalPerson(PersonBuilder b) {
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

    public static PersonBuilder create(String name) {
        return new PersonBuilder(name);
    }

    public static class PersonBuilder implements PetHolder {
        private final String name;
        private int age;
        private Pet pet;

        private PersonBuilder(String name) {
            this.name = name;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public <PETBUILDER extends AbstractPet.Builder<?, PET, PersonBuilder>, PET extends AbstractPet> PETBUILDER pet(
                PETBUILDER petBuilder) {
            petBuilder.setParent(this);
            return petBuilder;
        }

        @Override
        public void setPet(AbstractPet pet) {
            this.pet = pet;
        }

        public NormalPerson build() {
            final NormalPerson person = new NormalPerson(this);
            if (person.getPet() == null) {
                throw new NoPetException("Everyone must have pet!");
            }
            return person;
        }
    }
}
