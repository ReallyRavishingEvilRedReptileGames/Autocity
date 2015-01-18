package autocity.core.enumeration;

public enum ETerrainType {
    Grass {
        public char getCharacter() {
            return '.';
        }
    },
    Sand {
        public char getCharacter() {
            return '~';
        }
    },
    Water {
        public char getCharacter() {
            return ',';
        }
    },
    Clay {
        public char getCharacter() {
            return 'c';
        }
    },
    Rock {
        public char getCharacter() {
            return 'r';
        }
    },
    Marsh {
        public char getCharacter() {
            return 'm';
        }
    };

    public abstract char getCharacter();
}
