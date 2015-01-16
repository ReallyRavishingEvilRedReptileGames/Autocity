package autocity.core.character;

import autocity.core.enumeration.ECharacterSex;

/**
 * Biological sex only.
 */
public class Sex {
    private ECharacterSex sex;

    public Sex(ECharacterSex sex) {
        this.sex = sex;
    }

    public ECharacterSex getE() {
        return sex;
    }

    public void setSex(ECharacterSex sex) {
        this.sex = sex;
    }

    /**
     * Returns true if this sex can reproduce with another specified sex.
     * M-F pairings are obvious, but we also have hermaphrodites to make things a bit more fun.
     * They can reproduce with anyone.
     *
     * @param other
     * @return true if compatible
     */
    public boolean canReproduceWith(Sex other) {
        return this.sex == ECharacterSex.Hermaphrodite || other.sex == ECharacterSex.Hermaphrodite ||
                (this.sex == ECharacterSex.Male && other.sex == ECharacterSex.Female) ||
                (this.sex == ECharacterSex.Female && other.sex == ECharacterSex.Male);
    }

    public String toString() {
        switch (this.sex) {
            case Male:
                return "Male";
            case Female:
                return "Female";
            case Hermaphrodite:
                return "Hermaphrodite";
            default:
                return "Other";
        }
    }
}
