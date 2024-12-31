export interface Card {
    term: string;
    definition: string;
}

export interface Set {
    id: string;
    name: string;
    ownerUsername: string;
    dateCreated: string | null;
    visibility: boolean;
    cards: Card[];
}
