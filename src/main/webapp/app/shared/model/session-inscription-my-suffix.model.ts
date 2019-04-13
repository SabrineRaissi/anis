import { Moment } from 'moment';

export const enum Annee {
    PREMIERE_ANNEE = 'PREMIERE_ANNEE',
    DEUXIEME_ANNEE = 'DEUXIEME_ANNEE',
    TROSIEME_ANNEE = 'TROSIEME_ANNEE',
    QUATRIEME_ANNEE = 'QUATRIEME_ANNEE',
    CINQUIEME_ANNEE = 'CINQUIEME_ANNEE'
}

export interface ISessionInscriptionMySuffix {
    id?: number;
    startDate?: Moment;
    endDate?: Moment;
    annee?: Annee;
    theseId?: number;
    etablissementId?: number;
    statusSessionId?: number;
}

export class SessionInscriptionMySuffix implements ISessionInscriptionMySuffix {
    constructor(
        public id?: number,
        public startDate?: Moment,
        public endDate?: Moment,
        public annee?: Annee,
        public theseId?: number,
        public etablissementId?: number,
        public statusSessionId?: number
    ) {}
}
