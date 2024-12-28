/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{html,ts}',
  ],
  theme: {
    extend: {
      colors: {
        primary: '#0B131E',
        secondary: '#202B3B',
        tertiary: '#5688C7',
        textprimary: '#EEF4ED',
        textsecondary: '#CFDBD5',
      }
    },
  },
  plugins: [],
}

