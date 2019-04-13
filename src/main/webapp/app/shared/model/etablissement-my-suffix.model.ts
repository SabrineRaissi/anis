export interface IEtablissementMySuffix {
    id?: number;
    nom?: string;
}

export class EtablissementMySuffix implements IEtablissementMySuffix {
    constructor(public id?: number, public nom?: string) {}
}
