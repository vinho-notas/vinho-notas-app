module.exports = {
    testEnvironment: 'jest-environment-jsdom',
    setupFilesAfterEnv: ['<rootDir>/.jest/jest.setup.js'],
    moduleNameMapper: {
        '\\.(gif|tff|eot|svg|png)$': '<rootDir>/.jest/mocks/fileMock.js',
        '\\.(css|less|sass|scss)$': 'identity-obj-proxy',
    },

}