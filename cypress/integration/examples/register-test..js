context('Register test', () => {
    beforeEach(() => {
        cy.visit('https://asd-demo-app-249308.appspot.com/Register.html')
    })
    describe('Validation', () => {
        describe('Required Field', () => {


            it('should show missing required field, on send without any input', () => {
                cy.get('button[type=submit]')
                    .click()
            })

            it('should show missing required field, on insert nothing to them', () => {
                cy.get('input[name=firstName]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=lastName]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=email]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=phone]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=DOB]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=streetName]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=streetNumber]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=postcode]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=state]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=username]')
                    .oFocus_1()
                    .oBlur_2()
                cy.get('input[name=password]')
                    .oFocus_1()
                    .oBlur_2()

            })
        })
        describe('Email', () => {
            it('should show invalid email', () => {
                cy.get('input[name=email]')
                    .type('abc')
                    .blur()
            })
        })
        describe('firstName', () => {
            it('should show invalid first name', () => {
                cy.get('input[name=firstName]')
                    .type('344444')
                    .blur()
            })
        })
        describe('lastName', () => {
            it('should show invalid last name', () => {
                cy.get('input[name=email]')
                    .type('222222')
                    .blur()
            })
        })
        describe('phone', () => {
            it('should show invalid phone', () => {
                cy.get('input[name=phone]')
                    .type('2222222')
                    .blur()
            })
        })
    })
    describe('Success', () => {
        beforeEach(() => {

            cy.server()
            
            cy.route('GET', 'https://asd-demo-app-249308.appspot.com/Register.html', 'fixture:users.json')
                .as('registerRequest')
        })

        it('should redirect to success landing and show welcome message correctly', () => {
            cy.fixture('formData.json')
                .then((user) => {
                    cy.get('input[name=firstName]')
                        .type(user.firstName)
                    cy.get('input[name=lastName]')
                        .type(user.lastName)
                    cy.get('input[name=phone]')
                        .type(user.phone)
                    cy.get('input[name=email]')
                        .type(user.email)
                    cy.get('input[name=DOB]')
                        .type(user.DOB)
                    cy.get('input[name=streetName]')
                        .type(user.streetName)
                    cy.get('input[name=streetNumber]')
                        .type(user.streetNumber)
                    cy.get('input[name=postcode]')
                        .type(user.postcode)
                    cy.get('input[name=state]')
                        .type(user.state)
                    cy.get('input[name=username]')
                        .type(user.username)
                    cy.get('input[name=password]')
                        .type(user.password)
                    cy.get('button[type=submit]')
                        .click()

                    cy.wait('@registerRequest')
                        .then(({ request }) => {
                            expect(request.body).to.deep.equals(user)
                            cy.url().should('equals', `${Cypress.config().baseUrl}/success`)
                            cy.contains('Welcome ')
                        })
                })
        })
    })
})



