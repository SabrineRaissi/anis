import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';
import { IDoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';
import { DoctorantMySuffixService } from './doctorant-my-suffix.service';

@Component({
    selector: 'jhi-doctorant-my-suffix-update',
    templateUrl: './doctorant-my-suffix-update.component.html'
})
export class DoctorantMySuffixUpdateComponent implements OnInit {
    doctorant: IDoctorantMySuffix;
    isSaving: boolean;
    dateNissance: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected doctorantService: DoctorantMySuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ doctorant }) => {
            this.doctorant = doctorant;
            this.dateNissance = this.doctorant.dateNissance != null ? this.doctorant.dateNissance.format(DATE_TIME_FORMAT) : null;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.doctorant.dateNissance = this.dateNissance != null ? moment(this.dateNissance, DATE_TIME_FORMAT) : null;
        if (this.doctorant.id !== undefined) {
            this.subscribeToSaveResponse(this.doctorantService.update(this.doctorant));
        } else {
            this.subscribeToSaveResponse(this.doctorantService.create(this.doctorant));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoctorantMySuffix>>) {
        result.subscribe((res: HttpResponse<IDoctorantMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
