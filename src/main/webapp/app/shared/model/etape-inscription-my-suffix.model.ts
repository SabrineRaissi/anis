export interface IEtapeInscriptionMySuffix {
    id?: number;
    etape?: string;
    description?: string;
    nextId?: number;
    previousId?: number;
    statusEtapeId?: number;
    etablissementId?: number;
}

export class EtapeInscriptionMySuffix implements IEtapeInscriptionMySuffix {
    constructor(
        public id?: number,
        public etape?: string,
        public description?: string,
        public nextId?: number,
        public previousId?: number,
        public statusEtapeId?: number,
        public etablissementId?: number
    ) {}
}
