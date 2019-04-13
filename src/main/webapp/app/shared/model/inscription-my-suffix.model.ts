export interface IInscriptionMySuffix {
    id?: number;
    doctorantId?: number;
    theseId?: number;
    statusInscriptionId?: number;
    nextStepId?: number;
}

export class InscriptionMySuffix implements IInscriptionMySuffix {
    constructor(
        public id?: number,
        public doctorantId?: number,
        public theseId?: number,
        public statusInscriptionId?: number,
        public nextStepId?: number
    ) {}
}
