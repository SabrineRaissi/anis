/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DoctorantMySuffixService } from 'app/entities/doctorant-my-suffix/doctorant-my-suffix.service';
import { IDoctorantMySuffix, DoctorantMySuffix } from 'app/shared/model/doctorant-my-suffix.model';

describe('Service Tests', () => {
    describe('DoctorantMySuffix Service', () => {
        let injector: TestBed;
        let service: DoctorantMySuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IDoctorantMySuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(DoctorantMySuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new DoctorantMySuffix(
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateNissance: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a DoctorantMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateNissance: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateNissance: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new DoctorantMySuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a DoctorantMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        nationalite: 'BBBBBB',
                        dateNissance: currentDate.format(DATE_TIME_FORMAT),
                        sexe: 'BBBBBB',
                        etatCivil: 'BBBBBB',
                        adresse: 'BBBBBB',
                        profession: 'BBBBBB',
                        employeur: 'BBBBBB',
                        profilePic: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateNissance: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of DoctorantMySuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        nationalite: 'BBBBBB',
                        dateNissance: currentDate.format(DATE_TIME_FORMAT),
                        sexe: 'BBBBBB',
                        etatCivil: 'BBBBBB',
                        adresse: 'BBBBBB',
                        profession: 'BBBBBB',
                        employeur: 'BBBBBB',
                        profilePic: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateNissance: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a DoctorantMySuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
